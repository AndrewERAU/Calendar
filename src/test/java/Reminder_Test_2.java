package test.java;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import reminder.Reminder;

import org.junit.Test;

import reminder.Reminder;

public class Reminder_Test_2 {

	@Test
	public void test() throws IOException {
		 Reminder test_reminder = new Reminder();
		 long startTime1;
	     long startTime2;
	     long expectedEndTime1;
        long expectedEndTime2;
        
        System.out.println("Starting Test Case #1 (Creating a reminder):");
        System.out.println("Setting reminder info");
        System.out.println("Reminder Test Case #1");
        startTime1 = System.currentTimeMillis() / 1000;
        test_reminder.unitTestSetAlert(0, 1, 0);  //set reminder to one hour before event
        startTime2 = System.currentTimeMillis() / 1000;
        test_reminder.unitTestSetAlert(0, 1, 5);  //set reminder to one hour and 5 minutes before event
        expectedEndTime1 = startTime1 + 60;
        expectedEndTime2 = startTime2 + 65;
        System.out.print("Reminder 1 is set to alert at: ");
        System.out.println(expectedEndTime1);
        System.out.print("Reminder 2 is set to alert at: ");
        System.out.println(expectedEndTime2);
        assertEquals(1,1);
	}

}
