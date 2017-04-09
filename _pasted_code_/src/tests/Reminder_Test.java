package tests;

//Author: Nicholas DiPinto
//Reminder Test

import reminder.Reminder.java;
import org.junit.Test;
import static org.junit.Assert.*;

public class Reminder_Test
{
    public static void main(String args[])
    {
        long reminderTime1;
        long reminderTime2;
        long startTime1;
        long startTime2;
        long totalTime1;
        long totalTime2;
        
        System.out.println("Starting Test Case #1 (Creating a reminder):");
        System.out.println("Setting reminder info");
        private Reminder test_reminder = new Reminder();
        test_reminder.setTitle("Reminder Test Case #1");
        startTime1 = System.currentTimeMillis();
        reminderTime1 = test_reminder.setFirstAlertTime(60);
        startTime2 = System.currentTimeMillis();
        reminderTime2 = test_reminder.setSecondAlertTime(65);
        totalTime1 = reminderTime1 - startTime1;
        totalTime2 = reminderTime2 - startTime2;
        totalTime1 = totalTime1 / 1000;
        totalTime2 = totalTime2 / 1000;
        assertEqual(totalTime1, 60);
        assertEqual(totalTime2, 65);
        System.out.println("Succes of test.");
    }
}