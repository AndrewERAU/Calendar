package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import database.DatabaseMgr;
import event.Event;

public class Sql_Injection_Test {
	
	private final DatabaseMgr db = new DatabaseMgr();
	private String sqlInjection = "DELETE FROM Event;";
	private Event event = new Event("Meeting", // eventTitle
			"Meeting to go over plan details.", // eventDescription
			"2017-2-28", // eventDate
			"12:30:00", // eventStartTime
			"13:30:00", // eventEndTime
			"3500 Deer Creek Rd, Palo Alto, CA 94304", // eventLocation
			"", // eventInvitees
			"Work", // eventTag
			"",
			"");

	@Test
	public void test() {
		assertEquals(db.retrieveEvents('A',"").size(),0);
		db.insertEvent(event);
		assertEquals(db.retrieveEvents('A',"").size(),1);
		event.setEventReminder2("2017-02-28 11:30:00');" + sqlInjection + "--");
		db.insertEvent(event); // insertion should fail
		assertEquals(db.retrieveEvents('A',"").size(),1); 
	}

}
