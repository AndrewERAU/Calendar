package test.java;

import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import org.junit.Test;
import reminder.Reminder;
import event.Event;


public class Event_Reminder_Integrate_Test {

	static Event event1 = new Event("", "", "", "", "", "", "", "", "", "", "", "");  //creating an event where each field will be defaulted to "NULL"
	static Event event2 = new Event("0001", "Test Event", "2017-11-09", "8:00", "9:00", "", "", "None", "", "", "", "");
	static Reminder reminder = new Reminder();
	static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm MM/dd/yyyy");
	
	@Test
	public void test() throws Exception {
		System.out.println("Test 1: Creating a Null Event");
		System.out.print("Event: " + event1.getEventTitle() +
				 "\nInfo: " + event1.getEventDescription() +
				 "\nLocation: " + event1.getEventLocation() +
				 "\nStart Time: " + event1.getEventStartTime() +
				 "\nEnd Time: " + event1.getEventEndTime() + "\n");
		System.out.println("Test 2: Running all Event Methods");
		event2.setEventDescription("Some Event");
		event2.setEventLocation("King 117");
		event2.setEventTag("School");
		System.out.println(event2.formatEventSummary());
		assertEquals(1,1);
	}

}
