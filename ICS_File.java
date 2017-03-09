package file_io;

import java.io.BufferedWriter;
import java.io.FileWriter;
//import java.io.File;
import java.io.IOException;

import event.Event;

public class ICS_File {
	private String icsFile = null;
	private Event event = null;
	
	// TODO: change file export location to a user selected location
	public ICS_File (String filePath,Event eventToAdd) {
		icsFile = filePath;
		event = eventToAdd;
	}
	
	public void IcsExport() {
		String startTime = null;
		String endTime = null;
		String date = null;
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(icsFile))) {
			
			// first get data from event into proper format
			date = eventDateToIcsDate(event.getEventDate());
			startTime = eventTimeToIcsTime(event.getEventStartTime());
			endTime = eventTimeToIcsTime(event.getEventEndTime());

			String content = "BEGIN:VCALENDAR\r\n" +
						"VERSION:2.0\r\n" + 
						"BEGIN:VEVENT\r\n" +
						"DTSTART:" + date + startTime + "\r\n" +
						"DTEND:" + date + endTime + "\r\n" +
						"SUMMARY:" + event.getEventTitle() +"\r\n" +
						"END:VEVENT\r\n" +
						"END:VCALENDAR\r\n" +
						"";

			bw.write(content);

		} catch (IOException e) {

			e.printStackTrace();

		}
		/*BEGIN:VCALENDAR
VERSION:2.0
PRODID:-//hacksw/handcal//NONSGML v1.0//EN
BEGIN:VEVENT
UID:uid1@example.com
DTSTAMP:19970714T170000Z
ORGANIZER;CN=John Doe:MAILTO:john.doe@example.com
DTSTART:19970714T170000Z
DTEND:19970715T035959Z
SUMMARY:Bastille Day Party
END:VEVENT
END:VCALENDAR*/
		
	}
	
	private String eventTimeToIcsTime(String inTime) {
		// expects a time in hh:mm:ss format. 24-hour time
		// TODO: this could cause an error if input isnt in expected format!!!
		String output = null;
		System.out.println("inTime = " + inTime);
		output = "T";
		output += Character.toString(inTime.charAt(0)) + Character.toString(inTime.charAt(1));
		output += Character.toString(inTime.charAt(3)) + Character.toString(inTime.charAt(4));
		output += Character.toString(inTime.charAt(6)) + Character.toString(inTime.charAt(7));
		output += "Z";
		System.out.println("outTime = " + output);
		return output;
	}

	private String eventDateToIcsDate(String inDate) {
		// expects a date in yyyy-mm-dd format
		// TODO: this could cause an error if input isnt in expected format!!!
		System.out.println("inDate = " + inDate);
		String output = null;
		output = Character.toString(inDate.charAt(0)) + Character.toString(inDate.charAt(1)) + Character.toString(inDate.charAt(2)) + Character.toString(inDate.charAt(3));
		output += Character.toString(inDate.charAt(5)) + Character.toString(inDate.charAt(6));
		output += Character.toString(inDate.charAt(8)) + Character.toString(inDate.charAt(9));
		System.out.println("outDate = " + output);
		return output;
	}
}
