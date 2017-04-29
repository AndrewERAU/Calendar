package test.java;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import database.DatabaseMgr;
import event.Event;

public class Add_Event_3_Test {

		private Event event = new Event("track", // eventTitle
				"Run a lot.", // eventDescription
				"2017-4-20", // eventDate
				"04:00", // eventStartTime
				"19:00", // eventEndTime
				"Willow Creek", // eventLocation
				"johndoe@example.com", // eventInvitees
				"run, workout, improve", // eventTag
				"2017-4-20",
				"4:00",
				"2017-4-19",
				"14:00");
		
		@Test
		public void test() {
				
			File dbFile = new File(DatabaseMgr.DB_PATH);
			dbFile.delete();
			DatabaseMgr db = new DatabaseMgr();
			
			assertEquals(db.retrieveEvents('A',"").size(),0); // should be 0 to start
			
			db.insertEvent(event); // should succeed
			assertEquals(db.retrieveEvents('D',"2017-04-20").size(),1); // now 1 element
			assertEquals(db.retrieveEvents('A',"").size(),1); // should be 1 now

	}

}
