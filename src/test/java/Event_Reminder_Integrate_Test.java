package reminder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
public class Event_Reminder_Integrate_Test 
{
	static Event event = new Event();
	static Reminder reminder = new Reminder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm MM/dd/yyyy");
	
	public static void main(String args[]) throws IOException{
		System.out.println("Set event title: ");
		String etitle = input.readLine();
		event.setEventTitle(etitle);
		System.out.println("Set event description: ");
		String edescription = input.readLine();
		event.setEventDescription(edescription);
		System.out.println("Set event Location: ");
		String eLocation = input.readLine();
		event.setEventLocation(eLocation);
		event.setEventStartTime(4, 6, 2017, 21, 30);
		event.setEventEndTime(4, 6, 2017, 22, 00);
		System.out.println("Event filled");
		System.out.print("Event: " + event.getEventTitle() +
				 "\nInfo: " + event.getEventDescription() +
				 "\nLocation: " + event.getEventLocation() +
				 "\nStart Time: " + dateFormat.format(event.getEventStartTime()) +
				 "\nEnd Time: " + dateFormat.format(event.getEventEndTime()) + "\n");
		System.out.println("Set time to receive reminder:");
		System.out.println("How many days before event? ");
		Integer rdays = Integer.parseInt(input.readLine());
		System.out.println("How many hours before event? ");
		Integer rhours = Integer.parseInt(input.readLine());
		System.out.println("How many minutes before event? ");
		Integer rminutes = Integer.parseInt(input.readLine());
		reminder.setAlert(event, rdays, rhours, rminutes);
	}
}
