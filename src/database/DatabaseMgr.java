package database;

// TODO: Show error dialogs instead of printing error to console and exiting

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import event.Event;
import reminder.ReminderObj;

import time.Time;


public class DatabaseMgr {
	public static Integer eventsStartingInDB = 3;
	private Connection c = null;
	public final static String DB_DIR = "./db";
	public final static String TEST_DB_PATH = DB_DIR + "/test.db";
	public final static String SAMPLE_DB_PATH = DB_DIR + "/sample.db";
	public final static String DB_PATH = SAMPLE_DB_PATH; // set db path to the sample db.  should change this in production
	
	public DatabaseMgr() {
		File databaseFile = new File(DB_PATH);
		if ( !databaseFile.isFile() ) { // check if database file exists
			try {
				new File(DB_DIR).mkdirs(); // create the db directory if it doesn't exist
				                           // if it does exist this line wont do anything
				databaseFile.createNewFile();
			} catch (Exception e) {
				System.out.print("Error creating database file");
			}
			
			openDBConnection(); // initialize global db connection object
			createDB(); // run SQLite statements to create database tables
		} else {
			openDBConnection(); // initialize global db connection object
		}
	}
	
	private void createDB() {
        Statement stmt = null;
        
		try {
			stmt = c.createStatement();
		    String sql = "CREATE TABLE Event "+
		                   "(EventID INTEGER PRIMARY KEY," +
		                   " Title           TEXT    NOT NULL CHECK(Title<>''), " + // CHECK() is needed to enforce NOT NULL constraint
		                   " Description     TEXT, " + 
		                   " Date            DATE    NOT NULL CHECK(Date<>''), " + 
		                   " StartTime       TIME, " +
		                   " EndTime         TIME, " +
		                   " Location        TEXT, " +
		                   " Invitees        TEXT, " + // string of email addresses?
		                   " Tag             TEXT, " +
		                   " Reminder1Date       DATE, " +
		                   " Reminder1Time       TIME, " + // HH:MM
		                   " Reminder2Date       DATE, " +
		                   " Reminder2Time       TIME " + // HH:MM
		                   ")"; 
		    stmt.executeUpdate(sql);
		    stmt.close();
		} catch(Exception e) {
			System.out.println("Error creating database tables");
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(1);
		}
	}
	
	private void openDBConnection() {
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:"+DB_PATH);
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(1);
	    }
	}
	
	// TODO: I set attributes to 'NULL' in Event.java constructor if there is an empty string
	//       Therefore I have to allow 'NULL' in valid entry type when sanitizing statements
	//       Is this necessary or can I just have an empty string for un-set Event attributes?
	private boolean isAlphaNumeric(String s){
		// Modified slightly from this SO post:
		// http://stackoverflow.com/questions/11241690/regex-for-checking-if-a-string-is-strictly-alphanumeric
	    String pattern= "[a-zA-Z0-9\\s\\.,]*"; // ex) 'title' or ''  // also allows periods
	    if (s == null) return true;
	    return s.matches(pattern);
	}
	
	private boolean isValidDate(String s){
	    String pattern= "\\d{4}-\\d{1,2}-\\d{1,2}"; // ex) '2016-12-30'
	    if (s == null) return true;
	    return s.matches(pattern);
	}
	
	private boolean isValidReminderDate(String s){
	    String pattern= "(\\d{4}-\\d{1,2}-\\d{1,2})|(NULL)"; // ex) '2016-12-30'
	    if (s == null) return true;
	    return s.matches(pattern);
	}
	
	private boolean isValidTime(String s){
		// military time
	    //String pattern= "((\\d{2}:\\d{2}:\\d{2}){0,1})|(NULL)"; // ex) '12:30:00' or ''
		String pattern= "((\\d{1,2}:\\d{2}){0,1})|(NULL)"; // ex) 12:30 or ''
	    if (s == null) return true;
	    return s.matches(pattern);
	}
	
	private boolean isValidLocation(String s){
		// Modified slightly from this SO post:
		// http://stackoverflow.com/questions/11241690/regex-for-checking-if-a-string-is-strictly-alphanumeric
	    String pattern= "[a-zA-Z0-9\\s\\.,]*"; // ex) '3324 E. park rd, Jackson Missippi 86709' or ''
	    if (s == null) return true;
	    return s.matches(pattern);
	}
	
	private boolean isVaildEmailList(String s){
		// TODO: currently emails can only have letters and numbers, no underscores, dashes, etc.
	    String pattern= "(([a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+(,\\s*[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+)*)|(\\s*)|(NULL))*"; //ex) 'cool@star.com, beast99@rock.io' or 'cool@star.com' or ''
	    if (s == null) return true;
	    return s.matches(pattern);
	}
	
	
	private boolean sanitizeStatement(Event inEvent) {
		// Function to go through each attribute in the event to verify there are no illegal characters
		// An illegal character is one that could allow a SQL injection
		// note: with where this function is called from, each field will be enclosed in single quotes
		// TODO: Checks for entries with invalid characters, but doesn't check that entries make sense
		//        ex. dates should have months in range 1-12.

		return (isAlphaNumeric(inEvent.getEventTitle()) &&
				isAlphaNumeric(inEvent.getEventDescription()) &&
				isValidDate(inEvent.getEventDate()) &&
				isValidTime(inEvent.getEventStartTime()) &&
				isValidTime(inEvent.getEventEndTime()) &&
				isValidLocation(inEvent.getEventLocation()) &&
				isVaildEmailList(inEvent.getEventInvitees()) &&
				isAlphaNumeric(inEvent.getEventTag()) &&
				isValidReminderDate(inEvent.getEventReminder1Date()) &&
				isValidTime(inEvent.getEventReminder1Time()) &&
				isValidReminderDate(inEvent.getEventReminder2Date()) &&
				isValidTime(inEvent.getEventReminder2Time()));
	}
	
	public Event insertEvent(Event eventToAdd) { // returns eventID
		//Statement stmt = null;
		PreparedStatement stmt = null;
		int affectedRows; // used to check if insert was successful
		
		boolean rc;
		
		// TODO: Since changing to a prepared statement I no longer need to add single quotes!
		// remove this method
		//eventToAdd = eventToAdd.addSingleQuotes(); // must be called before creating
		          								   // insert statements
		
		try {
			
			rc = sanitizeStatement(eventToAdd);
			if (rc == false) { // invalid event data, possible sql injection attempt
				throw new Exception();
			}

		    String sql = "INSERT INTO Event " +
		                   "(Title,"+ 
		                   " Description, " + 
		                   " Date, " + 
		                   " StartTime, " +
		                   " EndTime, " +
		                   " Location, " +
		                   " Invitees, " + // string of email addresses?
		                   " Tag, " +
		                   " Reminder1Date, " +
		                   " Reminder1Time, " +
		                   " Reminder2Date, " +
		                   " Reminder2Time)" +
		    		       " VALUES  (?,?,?,?,?,?,?,?,?,?,?,?)";
		    
			stmt = c.prepareStatement(sql,
	                Statement.RETURN_GENERATED_KEYS);

		    try {
		    	 if (  !"".equals(eventToAdd.getEventTitle()) && !"NULL".equals(eventToAdd.getEventTitle()) ) {
		    		 stmt.setString(1, eventToAdd.getEventTitle());
				    } else { // title cannot be null
				    	throw new Exception(); // TODO: throw different exception type?
				    }
		    } catch (Exception e) {
		    	System.out.println("Error, event `Title` field cannot be empty");
		    	//System.exit(1);
		    }
		    
		    if ( !"".equals(eventToAdd.getEventDescription()) && !"NULL".equals(eventToAdd.getEventDescription()) ) {
		    	stmt.setString(2, eventToAdd.getEventDescription());
		    }
		    try {
		    	 if ( !"".equals(eventToAdd.getEventDate()) && !"NULL".equals(eventToAdd.getEventDate())) {
		    		 stmt.setString(3,eventToAdd.getEventDate());
				    } else { // date cannot be null
				    	throw new Exception(); // TODO: throw different exception type?
				    }
		    } catch (Exception e) {
		    	System.out.println("Error, event `Date` field cannot be empty");
		    	//System.exit(1);
		    }
		   
		    if ( !"".equals(eventToAdd.getEventStartTime()) ) {
		    	stmt.setString(4,eventToAdd.getEventStartTime());
		    }
		    if ( !"".equals(eventToAdd.getEventEndTime()) ) {
		    	stmt.setString(5,eventToAdd.getEventEndTime());
		    }
		    if ( !"".equals(eventToAdd.getEventLocation()) ) {
		    	stmt.setString(6,eventToAdd.getEventLocation());
		    }
		    if ( !"".equals(eventToAdd.getEventInvitees()) ) {
		    	stmt.setString(7,eventToAdd.getEventInvitees());
		    }
		    if ( !"".equals(eventToAdd.getEventTag()) ) {
		    	stmt.setString(8,eventToAdd.getEventTag());
		    }
		    if ( !"".equals(eventToAdd.getEventReminder1Date()) ) {
		    	stmt.setString(9,eventToAdd.getEventReminder1Date());
		    }
		    if ( !"".equals(eventToAdd.getEventReminder1Time()) ) {
		    	stmt.setString(10,eventToAdd.getEventReminder1Time());
		    }
		    if ( !"".equals(eventToAdd.getEventReminder2Date()) ) {
		    	stmt.setString(11,eventToAdd.getEventReminder2Date());
		    }
		    if ( !"".equals(eventToAdd.getEventReminder2Time()) ) {
		    	stmt.setString(12,eventToAdd.getEventReminder2Time());
		    }
		  
		    affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Inserting Event failed.");
	        }
	        
	        try {
	        	ResultSet generatedKeys = stmt.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                eventToAdd.setEventID(Long.toString(generatedKeys.getLong(1)));
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        } catch (Exception e) {
	        	System.err.println(e);
	        }
		    
		    stmt.close();
		} catch( Exception e ) {
			System.out.println("Error while inserting or updateing event");
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			e.printStackTrace();
			//System.exit(1);
		}
		
		return eventToAdd;
	}
	
	
	// Retrieves all reminders that are between the dates startDate and startDate + numDays inclusive
	public List<ReminderObj> retrieveReminders(String startDate) {
		Statement stmt = null;
		String sql = null;
		ResultSet data, data2;
		List<ReminderObj> reminders = new ArrayList<ReminderObj>();
		data = null;
		data2 = null;
		String sqlWhereClause = "";

		sqlWhereClause = " WHERE Reminder1Date >= '" + startDate + "' AND Reminder1Date <= '" + Time.incrementDate(startDate) + "';";

		try {
			stmt = c.createStatement();	
			sql = "SELECT Reminder1Date, Reminder1Time, Title FROM Event " + sqlWhereClause;
			data = stmt.executeQuery(sql); // must use data before closing stmt
			reminders = createListOfReminders(data,reminders); // put data returned from SQL statement into a vector
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		

		sqlWhereClause = " WHERE Reminder2Date >= '" + startDate + "' AND Reminder2Date <= '" + Time.incrementDate(startDate) + "';";

		try {
			stmt = c.createStatement();
			sql = "SELECT Reminder2Date, Reminder2Time, Title FROM Event " + sqlWhereClause;
			data2 = stmt.executeQuery(sql);		
			reminders = createListOfReminders(data2,reminders); // put data returned from SQL statement into a vector
	        // so we can work with it easier
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		return reminders;
	}
	
	public List<Event> retrieveEvents(char flag, String desiredData) { // control coupling
		Statement stmt = null;
		String sql;
		ResultSet data;
		List<Event> events = new ArrayList<Event>();
		data = null;
		String sqlWhereClause = "";
		
		try {
			switch (Character.toUpperCase(flag)) {
			case 'A': // all
				sqlWhereClause = ";"; // no where clause, just end SQL statement
				break;
			case 'D': // date
				sqlWhereClause = " WHERE Date = '" + desiredData + "';";
				break;
			case 'T': // title
				sqlWhereClause = " WHERE Title = '" + desiredData + "';";
				break;
			case 'I': // id
				sqlWhereClause = " WHERE EventID = " + desiredData  + ";";
				break;
			default:
				throw new Exception();
		}
		} catch (Exception e) {
			System.out.println("Invalid flag passed to retrieveEvents");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		try {
			stmt = c.createStatement();
			sql = "SELECT * FROM Event " + sqlWhereClause;
			data = stmt.executeQuery(sql);
			
			events = createListOfEvents(data); // put data returned from SQL statement into a vector
			                                   // so we can work with it easier
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		
		return events;
	}
	
	
	public Event updateEvent(Event eventToUpdate) {
		// TODO: need to get event ID and store it in event before we try to update it
		System.out.println(eventToUpdate.getEventID());
		removeEvent(Integer.parseInt(eventToUpdate.getEventID())); // remove old version of event
		eventToUpdate = insertEvent(eventToUpdate); // insert updated event. Event will have same id as original (old) event
		
		return eventToUpdate;
	}
	
	public void removeEvent(int id) {
		Statement stmt = null;
		String sql;
		
		try {
			stmt = c.createStatement();
			sql = "DELETE FROM Event WHERE EventID = " + Integer.toString(id) + ";"; 
		    stmt.executeUpdate(sql);
		    stmt.close();
		} catch( Exception e ) {
			System.out.println("Error deleting event");
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		//System.exit(1);
		}
	}
	
	private List<Event> createListOfEvents(ResultSet data) {
		List<Event> events = new ArrayList<Event>();
		
		try {
			while (data.next())
			{		
				
				events.add(new Event(data.getString(2),
						data.getString(3),
						data.getString(4),
						data.getString(5),
						data.getString(6),
						data.getString(7),
						data.getString(8),
						data.getString(9),
						data.getString(10),
						data.getString(11),
						data.getString(12),
						data.getString(13)));			
			}
			return events;
		} catch (Exception e) {
			System.out.println("Error creating list of events");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		return events;
	}
	
	private List<ReminderObj> createListOfReminders(ResultSet data, List<ReminderObj>listToReturn) {	
		try {
			while (data.next())
			{		
				listToReturn.add(new ReminderObj(data.getString(1), // date
						data.getString(2), // time
						data.getString(3))); // title	
			}

			return listToReturn;
		} catch (Exception e) {
			System.out.println("Error creating list of reminders");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		return listToReturn;
	}

	public void close() {
		try {
		  c.close();
	    }  catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(1);
	    }
	}
}
