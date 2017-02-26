package database;

// TODO: Show error dialogs instead of printing error to console and exiting

import java.io.File;
import java.sql.*;

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
			System.out.println("created db");
		} else {
			openDBConnection();
			System.out.println("opened db");
		}
		
		
	}
	
	private void createDB() {
        Statement stmt = null;
		
        
		
		try {
			stmt = c.createStatement();
		    String sql = "CREATE TABLE Event "+
		                   "(EventID INT PRIMARY KEY NOT NULL," +
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
	
	public void insertEvent(String eventTitle,
			  String eventDescription,
			  String eventDate,
			  String eventStartTime,
			  String eventEndTime,
			  String eventLocation,
			  String eventInvitees,
			  String eventTag) {
		Statement stmt = null;
		
		sanitizeStatement();
		
		try {
			stmt = c.createStatement();
			/*
		    String sql = "INSERT INTO Event VALUES  (" +
		                   eventTitle + "," +
		                   eventDescription + "," +
		                   eventDate + "," +
		                   eventStartTime + "," +
		                   eventEndTime + "," +
		                   eventLocation + "," +
		                   eventInvitees + "," +
		                   eventTag +
		    		       ")";*/
		    String sql = "INSERT INTO Event VALUES  (";
		    		       
		    try {
		    	 if (  !"".equals(eventTitle) ) {
				    	sql += "," + eventTitle; 
				    } else { // title cannot be null
				    	throw new Exception();
				    }
		    } catch (Exception e) {
		    	System.out.println("Error, event `Title` field cannot be empty");
		    	System.exit(1);
		    }
		    
		    if ( !"".equals(eventDescription) ) {
		    	sql += "," + eventDescription;
		    }
		    try {
		    	 if ( !"".equals(eventDate) ) {
				    	sql += "," + eventDate; 
				    } else { // date cannot be null
				    	throw new Exception();
				    }
		    } catch (Exception e) {
		    	System.out.println("Error, event `Date` field cannot be empty");
		    	System.exit(1);
		    }
		   
		    if ( !"".equals(eventStartTime) ) {
		    	sql += "," + eventStartTime;
		    }
		    if ( !"".equals(eventEndTime) ) {
		    	sql += "," + eventEndTime;
		    }
		    if ( !"".equals(eventLocation) ) {
		    	sql += "," + eventLocation;
		    }
		    if ( !"".equals(eventInvitees) ) {
		    	sql += "," + eventInvitees;
		    }
		    if ( !"".equals(eventTag) ) {
		    	sql += "," + eventTag;
		    }
		    
		    sql += ")";
		    
		    System.out.println(sql);
		    
		    		    
		    stmt.executeUpdate(sql);
		    stmt.close();
		} catch( Exception e ) {
			System.out.println("Error inserting event");
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(1);
		}

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
