package database;

// TODO: Show error dialogs instead of printing error to console and exiting

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import event.Event;

public class DatabaseMgr {
	private Connection c = null;
	public final static String DB_DIR = "./db";
	public final static String TEST_DB_PATH = DB_DIR + "/test.db";
	
	public DatabaseMgr() {
		File databaseFile = new File(TEST_DB_PATH);
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
		                   " Title           TEXT    NOT NULL, " + 
		                   " Description     TEXT, " + 
		                   " Date            DATE    NOT NULL, " + 
		                   " StartTime       TIME, " +
		                   " EndTime         TIME, " +
		                   " Location        TEXT, " +
		                   " Invitees        TEXT, " + // string of email addresses?
		                   " Tag             TEXT, " +
		                   " Reminder1       DATETIME, " +
		                   " Reminder2       DATETIME " +
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
	      c = DriverManager.getConnection("jdbc:sqlite:"+TEST_DB_PATH);
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(1);
	    }
	}
	
	private boolean isAlphaNumeric(String s){
		// Modified slightly from this SO post:
		// http://stackoverflow.com/questions/11241690/regex-for-checking-if-a-string-is-strictly-alphanumeric
	    String pattern= "^'[a-zA-Z0-9\\s\\.]*'$"; // ex) 'title' or ''  // also allows periods
	    if (s == null) return true;
	    return s.matches(pattern);
	}
	
	private boolean isValidDate(String s){
	    String pattern= "^'\\d{4}-\\d{1,2}-\\d{1,2}'$"; // ex) '2016-12-30'
	    if (s == null) return true;
	    return s.matches(pattern);
	}
	
	private boolean isValidTime(String s){
		// military time
	    String pattern= "^'(\\d{2}:\\d{2}:\\d{2}){0,1}'$"; // ex) '12:30:00' or ''
	    if (s == null) return true;
	    return s.matches(pattern);
	}
	
	private boolean isValidLocation(String s){
		// Modified slightly from this SO post:
		// http://stackoverflow.com/questions/11241690/regex-for-checking-if-a-string-is-strictly-alphanumeric
	    String pattern= "^'[a-zA-Z0-9\\s\\.,]*'$"; // ex) '3324 E. park rd, Jackson Missippi 86709' or ''
	    if (s == null) return true;
	    return s.matches(pattern);
	}
	
	private boolean isValidDatetime(String s){
	    String pattern= "^'(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}){0,1}'$"; // ex) '2017-01-01 10:00:00' or ''
	    if (s == null) return true;
	    return s.matches(pattern);
	}
	
	private boolean isVaildEmailList(String s){
		// TODO: currently emails can only have letters and numbers, no underscores, dashes, etc.
	    String pattern= "^'(([a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+(,\\s*[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+)*)|(\\s*))'$"; //ex) 'cool@star.com, beast99@rock.io' or 'cool@star.com' or ''
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
				isValidDatetime(inEvent.getEventReminder1()) &&
				isValidDatetime(inEvent.getEventReminder2()));
	}
	
	public void insertEvent(Event eventToAdd) {
		Statement stmt = null;
		boolean rc;
		
		eventToAdd = eventToAdd.addSingleQuotes(); // must be called before creating
		          								   // insert statements
		try {
			
			rc = sanitizeStatement(eventToAdd);
			if (rc == false) { // invalid event data, possible sql injection attempt
				throw new Exception();
			}
			
			stmt = c.createStatement();

		    String sql = "INSERT INTO Event " +
		                   "(Title,"+ 
		                   " Description, " + 
		                   " Date, " + 
		                   " StartTime, " +
		                   " EndTime, " +
		                   " Location, " +
		                   " Invitees, " + // string of email addresses?
		                   " Tag, " +
		                   " Reminder1, " +
		                   " Reminder2)" +
		    		       " VALUES  (";

		    try {
		    	 if (  !"".equals(eventToAdd.getEventTitle()) ) {
				    	sql += eventToAdd.getEventTitle(); 
				    } else { // title cannot be null
				    	throw new Exception(); // TODO: throw different exception type?
				    }
		    } catch (Exception e) {
		    	System.out.println("Error, event `Title` field cannot be empty");
		    	//System.exit(1);
		    }
		    
		    if ( !"".equals(eventToAdd.getEventDescription()) ) {
		    	sql += "," + eventToAdd.getEventDescription();
		    }
		    try {
		    	 if ( !"".equals(eventToAdd.getEventDate()) ) {
				    	sql += "," + eventToAdd.getEventDate(); 
				    } else { // date cannot be null
				    	throw new Exception(); // TODO: throw different exception type?
				    }
		    } catch (Exception e) {
		    	System.out.println("Error, event `Date` field cannot be empty");
		    	//System.exit(1);
		    }
		   
		    if ( !"".equals(eventToAdd.getEventStartTime()) ) {
		    	sql += "," + eventToAdd.getEventStartTime();
		    }
		    if ( !"".equals(eventToAdd.getEventEndTime()) ) {
		    	sql += "," + eventToAdd.getEventEndTime();
		    }
		    if ( !"".equals(eventToAdd.getEventLocation()) ) {
		    	sql += "," + eventToAdd.getEventLocation();
		    }
		    if ( !"".equals(eventToAdd.getEventInvitees()) ) {
		    	sql += "," + eventToAdd.getEventInvitees();
		    }
		    if ( !"".equals(eventToAdd.getEventTag()) ) {
		    	sql += "," + eventToAdd.getEventTag();
		    }
		    if ( !"".equals(eventToAdd.getEventReminder1()) ) {
		    	sql += "," + eventToAdd.getEventReminder1();
		    }
		    if ( !"".equals(eventToAdd.getEventReminder2()) ) {
		    	sql += "," + eventToAdd.getEventReminder2();
		    }
		    
		    sql += ");";
		    
		    System.out.println(sql); // for debugging - remove
		   
		    stmt.executeUpdate(sql);
		    stmt.close();
		} catch( Exception e ) {
			System.out.println("Error inserting event");
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			//System.exit(1);
		}
	}
	
	public List<Event> retrieveEvents(char flag, String desiredData) { // control coupling
		Statement stmt = null;
		String sql;
		ResultSet data;
		List<Event> events = new ArrayList<Event>();
		data = null;
		String sqlWhereClause = "";
		
		// TODO: This doesn't account for case 'A'.  Add case 'A' for retrieving all events
		//       then add default case that throws an exception for an unsupported flag
		switch (Character.toUpperCase(flag)) {
		case 'D': // date
			sqlWhereClause = " WHERE Date = '" + desiredData + "';";
			break;
		case 'T': // title
			sqlWhereClause = " WHERE Title = '" + desiredData + "';";
			break;
		case 'I': // id
			sqlWhereClause = " WHERE EventID = " + desiredData  + ";";
			break;
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
	
	/*
	public List<Event> retrieveEventByDate(String date) {
		Statement stmt = null;
		String sql;
		ResultSet data;
		List<Event> events = new ArrayList<Event>();
		
		data = null;
		
		try {
			stmt = c.createStatement();
			sql = "SELECT * FROM Event WHERE Date = '" + date + "';";
			
			data = stmt.executeQuery(sql);
			events = createListOfEvents(data);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		return events;
	}
	
	public List<Event> retrieveEventByTitle(String title) {
		Statement stmt = null;
		String sql;
		ResultSet data = null;
		List<Event> events = new ArrayList<Event>();
		
		
		try {
			stmt = c.createStatement();
			sql = "SELECT Title, " + 
	                   " Description, " + 
	                   " Date, " + 
	                   " StartTime, " +
	                   " EndTime, " +
	                   " Location, " +
	                   " Invitees, " + // string of email addresses?
	                   " Tag, " +
	                   " Reminder1, " +
	                   " Reminder2 " +
	                   "FROM Event WHERE Title = '" + title + "'";
			
			data = stmt.executeQuery(sql);
			events = createListOfEvents(data);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		return events;
	}

	public List<Event> retrieveEventByID(String id) {
		Statement stmt = null;
		String sql;
		ResultSet data;
		//final Event event;
		List<Event> events = new ArrayList<Event>();
		
		data = null;
		
		try {
			stmt = c.createStatement();
			
			sql = "SELECT Title, " + 
	                   " Description, " + 
	                   " Date, " + 
	                   " StartTime, " +
	                   " EndTime, " +
	                   " Location, " +
	                   " Invitees, " + // string of email addresses?
	                   " Tag, " +
	                   " Reminder1, " +
	                   " Reminder2 " +
	                   "FROM Event WHERE EventID = " + id + ";";
			
			data = stmt.executeQuery(sql);
			
			events = createListOfEvents(data);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		return events;
	}
	
	public List<Event> retrieveAllEvents() {
		Statement stmt = null;
		String sql;
		ResultSet data;
		//final Event event;
		List<Event> events = new ArrayList<Event>();
		
		data = null;
		
		try {
			stmt = c.createStatement();
			
			sql = "SELECT Title, " + 
	                   " Description, " + 
	                   " Date, " + 
	                   " StartTime, " +
	                   " EndTime, " +
	                   " Location, " +
	                   " Invitees, " + // string of email addresses?
	                   " Tag, " +
	                   " Reminder1, " +
	                   " Reminder2 " +
	                   "FROM Event";
			
			data = stmt.executeQuery(sql);
			
			events = createListOfEvents(data);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		return events;
	}
	*/
	
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
				events.add(new Event(data.getString(1),
						data.getString(2),
						data.getString(3),
						data.getString(4),
						data.getString(5),
						data.getString(6),
						data.getString(7),
						data.getString(8),
						data.getString(9),
						data.getString(10)));			
			}
			return events;
		} catch (Exception e) {
			System.out.println("Error creating list of events");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		return events;
	}
	
	public void update() {
		// make sure to call sanitizeStatements(eventToUpdate);
		
	}
	
	public void delete() {
		
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
