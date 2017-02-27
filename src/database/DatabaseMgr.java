package database;

// TODO: Show error dialogs instead of printing error to console and exiting

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import event.Event;

public class DatabaseMgr {
	public Connection c = null;
	
	public DatabaseMgr() {
		File databaseFile = new File("./db/test.db");
		if ( !databaseFile.isFile() )
		{
			try {
				new File("./db").mkdirs(); // create the db directory if it doesnt exist
				databaseFile.createNewFile();
			} catch (Exception e) {
				System.out.print("Error creating database file");
			}
			
			openDBConnection();
			createDB();
		} else {
			openDBConnection();
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
	      // TODO: next line fails if path doesn't exist.
	      // have code check to see if ./db folder exists, and if not, make it
	      c = DriverManager.getConnection("jdbc:sqlite:./db/test.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(1);
	    }
	}
	
	private void sanitizeStatement() {
		
	}
	
	public void insertEvent(Event eventToAdd) {
		Statement stmt = null;
		
		sanitizeStatement(); // TODO: Implement this
		
		eventToAdd = eventToAdd.addSingleQuotes(); // must be called before creating
		          								   // insert statements
		
		try {
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
		    //sql += "NULL";
		    try {
		    	 if (  !"".equals(eventToAdd.getEventTitle()) ) {
				    	sql += eventToAdd.getEventTitle(); 
				    } else { // title cannot be null
				    	throw new Exception();
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
				    	throw new Exception();
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
		    
		    System.out.println(sql);
		    
		    		    
		    stmt.executeUpdate(sql);
		    stmt.close();
		} catch( Exception e ) {
			System.out.println("Error inserting event");
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			//System.exit(1);
		}

	}
	
	public List<Event> retrieveEventByDate(String date) {
		Statement stmt = null;
		String sql;
		ResultSet data;
		List<Event> events = new ArrayList<Event>();
		
		data = null;
		
		try {
			stmt = c.createStatement();
			/*sql = "SELECT Title, " + 
	                   " Description, " + 
	                   " Date, " + 
	                   " StartTime, " +
	                   " EndTime, " +
	                   " Location, " +
	                   " Invitees, " + // string of email addresses?
	                   " Tag, " +
	                   " Reminder1, " +
	                   " Reminder2 " +
	                   "FROM Event WHERE Date = '" + date + "';";*/
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
