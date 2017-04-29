package test.java;
import static org.junit.Assert.*;

import java.io.File;

import database.DatabaseMgr;
import event.Event;

import org.junit.Test;

public class Add_Event_Test {

	private Event event = new Event("", // eventTitle
			"Meeting to go over plan details.", // eventDescription
			"", // eventDate
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
		
		// TODO: Update.  Database is allowing null into not null fields? or is it allowing empty string?
		assertEquals(db.retrieveEvents('A',"").size(),0); // should be 0 to start
		db.insertEvent(event); // should fail
		assertEquals(db.retrieveEvents('A',"").size(),0); // should still be 0
		
		event.setEventTitle("Meeting");
		db.insertEvent(event); // should fail *no date*
		assertEquals(db.retrieveEvents('T',"Meeting").size(),0); // should still be 0
		
		event.setEventDate("2017-2-27");
		db.insertEvent(event); // should succeed
		assertEquals(db.retrieveEvents('D',"2017-02-27").size(),1); // now 1 element
		assertEquals(db.retrieveEvents('A',"").size(),1); // should be 1 now
		
		event.setEventReminder1Date("2017-2-27");
		event.setEventReminder1Time("9:00");
		db.insertEvent(event); // should succeed
		assertEquals(db.retrieveEvents('D',"2017-02-27").size(),2); // now 1 element
		assertEquals(db.retrieveEvents('A',"").size(),2); // should be 1 now
	}

}
