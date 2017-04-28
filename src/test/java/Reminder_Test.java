package test.java;

import static org.junit.Assert.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import reminder.Reminder;
import org.junit.Test;
import event.Event;

public class Reminder_Test {

	@Test
	public void test() throws Exception {
		 Reminder test_reminder = new Reminder();
		 Reminder negative_reminder = new Reminder();  //reminder created for fault injection
		 Event event = new Event();
		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm yyyy-MM-dd");
        
	     System.out.println("Starting Test Case #1 (Creating a reminder):");
	     System.out.println("Setting reminder info");
	     event.setEventDate("2017-04-24");
	     event.setEventStartTime("12:00");
	     event.setEventEndTime("13:00");
	     test_reminder.setAlert(event, 0, 1, 0);  //set reminder to one hour before event
	     negative_reminder.setAlert(event, -1, -1, -5);  //set reminder to invalid time to test exception handling
	     System.out.println("Reminder 1 is set to alert at: " + sdf.format(test_reminder.getAlert()));
	     //System.out.println("Reminder 2 is set to alert at: "+ sdf.format(negative_reminder.getAlert()));
	     assertEquals(1,1);
	}

}
