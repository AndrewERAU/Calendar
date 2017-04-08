package event;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reminder
{
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
    
    public void setAlert(Event event, Integer numDays, Integer numHours, Integer numMinutes) throws FileNotFoundException
    {
		final long MINUTE = 60000; // in milli-seconds.
		final long HOUR = 60 * MINUTE; // in milli-seconds.
		final long DAY = 24 * HOUR; // in milli-seconds
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm MM/dd/yyyy");
		String event_content = "Event: " + event.getEventTitle() +
							 "\nInfo: " + event.getEventDescription() +
							 "\nLocation: " + event.getEventLocation() +
							 "\nStart Time: " + dateFormat.format(event.getEventStartTime()) +
							 "\nEnd Time: " + dateFormat.format(event.getEventEndTime());  //write out event info
		String Filename = "reminder_message.txt";
		PrintWriter pw = new PrintWriter(Filename);
		this.alertTime = new Date(event.getEventStartTime().getTime() - (numDays * DAY) - (numHours * HOUR) - (numMinutes * MINUTE));
		pw.print("");
		pw.print(event_content);
		try {
          String command = "mail -s \"Event Reminder\" pinto18.nd@gmail.com < " + Filename + "| at " + dateFormat.format(alertTime);
		  //Process p = Runtime.getRuntime().exec(command);
			Process p = Runtime.getRuntime().exec("cat " + Filename + "| at " + dateFormat.format(alertTime));
            System.out.println(command);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		pw.close();
    }
 
}

