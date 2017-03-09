//Author: Nicholas DiPinto
//Reminder Test

package tests;
import static org.junit.Assert.*;

import event.Event;
import event.Reminder;
import org.junit.Test;

public class Reminder_Test
{
    System.out.println("Starting Test Case #1 (Creating a reminder):");
    System.out.println("Setting reminder info");
    private Reminder test_reminder = new Reminder();
    test_reminder.setTitle("Test Case #1 Reminder");
    test_reminder.setFirstAlertTime(60);
    System.out.println("Print out time reminder is expected to appear.");
    System.out.println("When reminder appears, print current time and how much time has passed since reminder timer was set.");
    
    System.out.println("Starting Test Case #2 (Creating two reminders):");
    System.out.println("Setting reminder info");
    private Reminder test_reminder1 = new Reminder();
    private Reminder test_reminder2 = new Reminder();
    test_reminder1.setTitle("Test Case #2 Reminder 1");
    test_reminder1.setTitle("Test Case #2 Reminder 2");
    test_reminder1.setFirstAlertTime(60);
    test_reminder1.setSecondAlertTIme(60);
    test_reminder1.setFirstAlertTime(60);
    test_reminder1.setSecondAlertTIme(60);
    System.out.println("Print out time both reminders are expected to appear.");
    System.out.println("When reminders appear, print current time and how much time has passed since each reminder timer was set.");
}
