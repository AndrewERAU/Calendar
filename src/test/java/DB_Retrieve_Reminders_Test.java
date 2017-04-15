package test.java;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import database.DatabaseMgr;
import event.Event;
import reminder.ReminderObj;

public class DB_Retrieve_Reminders_Test {
	
	private Event event = new Event("My Reminder Test Title", // eventTitle
			"Meeting to go over plan details.", // eventDescription
			"2017-04-14", // eventDate
			"12:30", // eventStartTime
			"13:30", // eventEndTime
			"3500 Deer Creek Rd, Palo Alto, CA 94304", // eventLocation
			"", // eventInvitees
			"Work", // eventTag
			"2017-04-14", // Reminder1Date
			"13:30", // Reminder1Time
			"2017-04-14", // Reminder2Date
			"08:00"); // Reminder2Time

	@Test
	public void test() {
		File dbFile = new File(DatabaseMgr.TEST_DB_PATH);
		dbFile.delete();
		DatabaseMgr db = new DatabaseMgr();
		
		assertEquals(db.retrieveEvents('A',"").size(),0); // should be 0 to start
		db.insertEvent(event);	
		assertEquals(db.retrieveReminders("2017-04-06").size(),0); // first event had two reminders within 7 days of this date
		assertEquals(db.retrieveReminders("2017-04-07").size(),2); // first event had two reminders within 7 days of this date
		assertEquals(db.retrieveReminders("2017-04-08").size(),2); // first event had two reminders within 7 days of this date
		assertEquals(db.retrieveReminders("2017-04-09").size(),2); // first event had two reminders within 7 days of this date
		assertEquals(db.retrieveReminders("2017-04-10").size(),2); // first event had two reminders within 7 days of this date
		assertEquals(db.retrieveReminders("2017-04-11").size(),2); // first event had two reminders within 7 days of this date
		assertEquals(db.retrieveReminders("2017-04-12").size(),2); // first event had two reminders within 7 days of this date
		assertEquals(db.retrieveReminders("2017-04-13").size(),2); // first event had two reminders within 7 days of this date
		assertEquals(db.retrieveReminders("2017-04-14").size(),2); // first event had two reminders within 7 days of this date
		assertEquals(db.retrieveReminders("2017-04-15").size(),0); // first event had two reminders within 7 days of this date
	}

}
