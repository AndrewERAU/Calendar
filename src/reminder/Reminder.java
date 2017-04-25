package reminder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//import java.util.concurrent.TimeUnit;

import event.Event;

public class Reminder
{
	// TODO: rename this to SendReminderToServer
	// This class will handle sending events to server
	private Date alertTime = null;
	
	public Reminder()
    {
		alertTime = null;
        System.out.println("Reminder object was created.");
	}
    
    public Date getAlert()
    {
        return this.alertTime;
    }
    
    public void setAlert(Event event, Integer numDays, Integer numHours, Integer numMinutes) throws Exception
    {
		final long MINUTE = 60000; // in milli-seconds.
		final long HOUR = 60 * MINUTE; // in milli-seconds.
		final long DAY = 24 * HOUR; // in milli-seconds
		try
		{
		if(numDays < 0 | numHours < 0 | numMinutes < 0)
			throw new Exception();  //exception handler for invalid reminder times
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm yyyy-M-d");
		String event_content = "Event: " + event.getEventTitle() +
							 "\nInfo: " + event.getEventDescription() +
							 "\nLocation: " + event.getEventLocation() +
							 "\nStart Time: " + dateFormat.format(event.getEventStartDateTime()) +
							 "\nEnd Time: " + dateFormat.format(event.getEventEndDateTime());  //write out event info
		String Filename = "reminder_message.txt";
		PrintWriter pw = new PrintWriter(Filename);
		this.alertTime = new Date(event.getEventStartDateTime().getTime() - (numDays * DAY) - (numHours * HOUR) - (numMinutes * MINUTE));
		pw.print("");
		pw.print(event_content);
          String command = "mail -s \"Event Reminder\" pinto18.nd@gmail.com < " + Filename + "| at " + dateFormat.format(alertTime);
		  //Process p = Runtime.getRuntime().exec(command);
			Process p = Runtime.getRuntime().exec("cat " + Filename + "| at " + dateFormat.format(alertTime));
            System.out.println(command);
        	pw.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Invalid Input");
		}	
    }
}

