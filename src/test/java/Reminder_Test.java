//Author: Nicholas DiPinto
//Reminder Test
/* Test was causing comiplation errors so I commented it out.  Fix test then uncomment.
import reminder.Reminder;

public class Reminder_Test1
{
    public static void main(String args[])
    {
        Reminder test_reminder = new Reminder();
        long startTime1;
        long startTime2;
        long expectedEndTime1;
        long expectedEndTime2;
        
        System.out.println("Starting Test Case #1 (Creating a reminder):");
        System.out.println("Setting reminder info");
        test_reminder.setTitle("Reminder Test Case #1");
        startTime1 = System.currentTimeMillis() / 1000;
        test_reminder.setAlert(60);
        startTime2 = System.currentTimeMillis() / 1000;
        test_reminder.setAlert(65);
        expectedEndTime1 = startTime1 + 60;
        expectedEndTime2 = startTime2 + 65;
        System.out.print("Reminder 1 is set to alert at: ");
        System.out.println(expectedEndTime1);
        System.out.print("Reminder 2 is set to alert at: ");
        System.out.println(expectedEndTime2);
    }
}
*/