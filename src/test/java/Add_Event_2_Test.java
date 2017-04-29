package test.java;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import database.DatabaseMgr;
import event.Event;

public class Add_Event_2_Test {


	private Event event = new Event("Meeting", // eventTitle
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
		
		assertEquals(db.retrieveEvents('A',"").size(),0); // should be 0 to start
		
		event.setEventDate("2017-2-27");
		db.insertEvent(event); // should succeed
		assertEquals(db.retrieveEvents('D',"2017-02-27").size(),1); // now 1 element
		assertEquals(db.retrieveEvents('A',"").size(),1); // should be 1 now

		event.setEventDate("2017-2-01");
		db.insertEvent(event); // should succeed
		assertEquals(db.retrieveEvents('D',"2017-02-01").size(),1); // now 1 element
		
		event.setEventDate("2017-02-01");
		db.insertEvent(event); // should succeed
		assertEquals(db.retrieveEvents('D',"2017-02-27").size(),1); // now 1 element
		
		event.setEventDate("2017-02-1");
		db.insertEvent(event); // should succeed
		assertEquals(db.retrieveEvents('D',"2017-02-27").size(),1); // now 1 element
		
		event.setEventDate("2017-2-1");
		db.insertEvent(event); // should succeed
		assertEquals(db.retrieveEvents('D',"2017-02-27").size(),1); // now 1 element
		
	}

}
