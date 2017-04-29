package test.java;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import database.DatabaseMgr;
import event.Event;

public class Sql_Injection_Test {
	
	private String sqlInjection = "DELETE FROM Event;";
	private Event event = new Event("Meeting", // eventTitle
			"Meeting to go over plan details.", // eventDescription
			"2017-2-28", // eventDate
			"12:30", // eventStartTime
			"13:30", // eventEndTime
			"3500 Deer Creek Rd, Palo Alto, CA 94304", // eventLocation
			"", // eventInvitees
			"Work", // eventTag
			"",
			"",
			"",
			"");

	@Test
	public void test() {
		File dbFile = new File(DatabaseMgr.DB_PATH);
		dbFile.delete();
		DatabaseMgr db = new DatabaseMgr();
		assertEquals(db.retrieveEvents('A',"").size(),0);
		db.insertEvent(event);
		assertEquals(db.retrieveEvents('A',"").size(),1);
		event.setEventReminder2Time("'11:30:00');" + sqlInjection + "--");
		db.insertEvent(event); // insertion should fail
		assertEquals(db.retrieveEvents('A',"").size(),1); 
	}

}
