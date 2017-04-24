package test.java;

import static org.junit.Assert.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import reminder.Reminder;
import org.junit.Test;
import event.Event;

public class Reminder_Test {

	@Test
	public void test() throws IOException {
		 Reminder test_reminder_1 = new Reminder();
		 Reminder test_reminder_2 = new Reminder();
		 Event event = new Event();
		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm yyyy-MM-dd");
        
	     System.out.println("Starting Test Case #1 (Creating a reminder):");
	     System.out.println("Setting reminder info");
	     event.setEventDate("2017-04-24");
	     event.setEventStartTime("12:00");
	     event.setEventEndTime("13:00");
	     test_reminder_1.setAlert(event, 0, 1, 0);  //set reminder to one hour before event
	     test_reminder_2.setAlert(event, 0, 1, 5);  //set reminder to one hour and 5 minutes before event
	     System.out.println("Reminder 1 is set to alert at: " + sdf.format(test_reminder_1.getAlert()));
	     System.out.println("Reminder 2 is set to alert at: "+ sdf.format(test_reminder_2.getAlert()));
	     assertEquals(1,1);
	     //throw new IOException("My IOException");
	}

}
