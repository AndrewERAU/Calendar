package test.java;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import database.DatabaseMgr;
import event.Event;

public class Update_Event_Test {
	private Event event = new Event("My favorite event", // eventTitle
			"Meeting to go over plan details.", // eventDescription
			"2017-05-20", // eventDate
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
		File dbFile = new File(DatabaseMgr.TEST_DB_PATH);
		dbFile.delete();
		DatabaseMgr db = new DatabaseMgr();
		
		assertEquals(db.retrieveEvents('A',"").size(),0); // should be 0 to start
		event = db.insertEvent(event); // should succeed
		assertEquals(db.retrieveEvents('T',"My favorite event").size(),1); // now 1 element
		
		event.setEventTitle("Meeting");
		
		event = db.updateEvent(event); // should succeed
		assertEquals(db.retrieveEvents('T',"My favorite event").size(),0); // now 0 element
		assertEquals(db.retrieveEvents('T',"Meeting").size(),1); // now 1 element
		assertEquals(db.retrieveEvents('A',"").size(),1); // should be 0 to start
	}
}