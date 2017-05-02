package file_io;

import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.data.CalendarOutputter;
import java.util.GregorianCalendar;
import net.fortuna.ical4j.model.component.VEvent;
import java.io.FileOutputStream;
import java.io.*;

import event.Event;

public class ICS_File {
	private String icsFile = null;
	private Calendar calendar = null;
	
	public ICS_File () {
		icsFile = "calendar_export.ics"; // Default ICS filename
		initializeCalendar();
	}
	
	public ICS_File (String filePath) {
		icsFile = filePath;
		initializeCalendar();
	}
	
	public void initializeCalendar() {
		calendar = new Calendar();
		calendar.getProperties().add(new ProdId("-//Events Calendar//iCal4j 2.0//EN"));
		calendar.getProperties().add(Version.VERSION_2_0);
		calendar.getProperties().add(CalScale.GREGORIAN);
	}
	
	public void IcsAddEvent(Event eventToAdd) {
		
		// Date Parsing
		String eventDate = eventToAdd.getEventDate();
		int eventYear = Integer.parseInt(eventDate.split("\\-")[0]);
		int eventMonth = Integer.parseInt(eventDate.split("\\-")[1]) - 1;
		int eventDay = Integer.parseInt(eventDate.split("\\-")[2]);
		
		// Start Time Parsing
		String startTime = eventToAdd.getEventStartTime();
		int startHour = Integer.parseInt(startTime.split("\\:")[0]);
		int startMinute = Integer.parseInt(startTime.split("\\:")[1]);
		
		// End Time Parsing
		String endTime = eventToAdd.getEventStartTime();
		int endHour = Integer.parseInt(endTime.split("\\:")[0]);
		int endMinute = Integer.parseInt(endTime.split("\\:")[1]);
		
		 // Start Date
		java.util.Calendar startDate = new GregorianCalendar();
		startDate.set(java.util.Calendar.MONTH, eventMonth);
		startDate.set(java.util.Calendar.DAY_OF_MONTH, eventDay);
		startDate.set(java.util.Calendar.YEAR, eventYear);
		startDate.set(java.util.Calendar.HOUR_OF_DAY, startHour);
		startDate.set(java.util.Calendar.MINUTE, startMinute);
		startDate.set(java.util.Calendar.SECOND, 0);
		
		// End Date
		java.util.Calendar endDate = new GregorianCalendar();
		endDate.set(java.util.Calendar.MONTH, eventMonth);
		endDate.set(java.util.Calendar.DAY_OF_MONTH, eventDay);
		endDate.set(java.util.Calendar.YEAR, eventYear);
		endDate.set(java.util.Calendar.HOUR_OF_DAY, endHour);
		endDate.set(java.util.Calendar.MINUTE, endMinute);	
		endDate.set(java.util.Calendar.SECOND, 0);
		
		// Create the event
		String eventName = eventToAdd.getEventTitle();
		DateTime start = new DateTime(startDate.getTime());
		DateTime end = new DateTime(endDate.getTime());
		VEvent event = new VEvent(start, end, eventName);

		// Add the event and print
		calendar.getComponents().add(event);
		//System.out.println(icsCalendar);
	}
	
	public void IcsExport() throws FileNotFoundException, IOException {
		FileOutputStream fout = new FileOutputStream(icsFile);

		CalendarOutputter outputter = new CalendarOutputter();
		outputter.output(calendar, fout);
	}
}
/**/