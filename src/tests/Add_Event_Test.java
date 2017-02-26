package tests;
import static org.junit.Assert.*;

import database.DatabaseMgr;
import event.Event;

import org.junit.Test;

public class Add_Event_Test {

	private final DatabaseMgr db = new DatabaseMgr();
	private Event event = new Event("", // eventTitle
			"Meeting to go over plan details.", // eventDescription
			"", // eventDate
			"12:30:00", // eventStartTime
			"13:30:00", // eventEndTime
			"3500 Deer Creek Rd, Palo Alto, CA 94304", // eventLocation
			"", // eventInvitees
			"Work", // eventTag
			"",
			"");
	
	@Test
	public void test() {
		
		db.insertEvent(event);
		db.retrieveAllEvents();
		
		event.setEventTitle("Meeting");
		db.insertEvent(event);
		db.retrieveEventByTitle("Meeting");
		
		event.setEventDate("2017-02-27");
		db.insertEvent(event);
		db.retrieveEventByDate("2017-02-27");
	}

}
