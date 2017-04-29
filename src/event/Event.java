package event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import time.Time;

public class Event {
	private String eventID;
	private String eventTitle;
	private String eventDescription;
	private String eventDate;
	private String eventStartTime;
	private String eventEndTime;
	private String eventLocation;
	private String eventInvitees;
	private String eventTag;
	private String eventReminder1Date;
	private String eventReminder1Time;
	private String eventReminder2Date;
	private String eventReminder2Time;
	
	public Event() {
		this.eventID = null;
		this.eventTitle = null;
		this.eventDescription = null;
		this.eventDate = null;
		this.eventStartTime = null;
		this.eventEndTime = null;
		this.eventLocation = null;
		this.eventInvitees = null;
		this.eventTag = null;
		this.eventReminder1Date = null;
		this.eventReminder1Time = null;
		this.eventReminder2Date = null;
		this.eventReminder2Time = null;
	}
	
	public Event(String eventTitle,
			  String eventDescription,
			  String eventDate,
			  String eventStartTime,
			  String eventEndTime,
			  String eventLocation,
			  String eventInvitees,
			  String eventTag,
			  String eventReminder1Date,
			  String eventReminder1Time,
			  String eventReminder2Date,
			  String eventReminder2Time) {
		
		this.eventID = null;
		if (!"".equals(eventTitle)) {
			this.eventTitle =  eventTitle;
		} else {
			this.eventTitle = "NULL";
		}
		if (!"".equals(eventDescription)) {
			this.eventDescription = eventDescription;
		} else {
			this.eventDescription = "NULL";
		}
		if (!"".equals(eventDate) && !"NULL".equals(eventDate)) {
			this.setEventDate(eventDate);
		} else {
			this.eventDate = "NULL";
		}
		if (!"".equals(eventStartTime)) {
			this.eventStartTime = eventStartTime;
		} else {
			this.eventStartTime = "NULL";
		}
		if (!"".equals(eventEndTime)) {
			this.eventEndTime = eventEndTime;
		} else {
			this.eventEndTime = "NULL";
		}
		if (!"".equals(eventLocation)) {
			this.eventLocation = eventLocation;
		} else {
			this.eventLocation = "NULL";
		}
		if (!"".equals(eventInvitees)) {
			this.eventInvitees = eventInvitees;
		} else {
			this.eventInvitees = "NULL";
		}
		if (!"".equals(eventTag)) {
			this.eventTag = eventTag;
		} else {
			this.eventTag = "NULL";
		}	
		if (!"".equals(eventReminder1Date) && !"NULL".equals(eventReminder1Date)) {
			this.setEventReminder1Date(eventReminder1Date);
		} else {
			this.eventReminder1Date = "NULL";
		}
		if (!"".equals(eventReminder1Time)) {
			this.eventReminder1Time = eventReminder1Time;
		} else {
			this.eventReminder1Time = "NULL";
		}
		if (!"".equals(eventReminder2Date) && !"NULL".equals(eventReminder2Date)) {
			this.setEventReminder2Date(eventReminder2Date);
		} else {
			this.eventReminder2Date = "NULL";
		}
		if (!"".equals(eventReminder2Time)) {
			this.eventReminder2Time = eventReminder2Time;
		} else {
			this.eventReminder2Time = "NULL";
		}

	}
	
	// TODO: This code was copied from reminderObj constructor
	// Maybe put it in a seaparte file and import it so there is only one copy?
	// call it formatSummary() ???
	public String formatEventSummary() {
		
		String dayOfWeek = Time.getWeekdayFromString(getEventDate(),true); // true means convert weekday to word.  ex) "Sunday", not "0"
		String month = Time.getMonthFromString(getEventDate(),true); // true means convert month to word.  ex) "January", not "1"
		String day = Time.getDayFromString(getEventDate());
		String time = Time.getCivilianTimeFromString(getEventStartTime());
		String eventTitle = getEventTitle();	
		
		return ("<b>"+dayOfWeek+", "+month+" "+day+" - "+time+":<br></b>"+eventTitle+"<br><br>");
	}
	
	public String formatEvent() {
		
		String dayOfWeek = Time.getWeekdayFromString(getEventDate(),true); // true means convert weekday to word.  ex) "Sunday", not "0"
		String month = Time.getMonthFromString(getEventDate(),true); // true means convert month to word.  ex) "January", not "1"
		String day = Time.getDayFromString(getEventDate());
		String time = Time.getCivilianTimeFromString(getEventStartTime());
		String eventTitle = getEventTitle();	
		String endTime = Time.getCivilianTimeFromString(getEventEndTime());
		// tag, location,invitees
		return ("<b>"+dayOfWeek+", "+month+" "+day+" - "+time+"-"+endTime+":<br></b>"+ 
				"<b><u>Title:</u></b> "+eventTitle+"<br>"+
				"<b><u>Description:</u></b> "+getEventDescription()+"<br>"+
				"<b><u>Tag:</u></b> " + getEventTag() +"<br>"+
				"<b><u>Location:</u></b> " + getEventLocation() +"<br>"+
				"<b><u>Invitees:</u></b> " + getEventInvitees() +"<br>"+
				"<b><u>Reminder 1:</u></b> " + getEventReminder1Date() + " " + getEventReminder1Time() + "<br>"+
				"<b><u>Reminder 2:</u></b> " + getEventReminder2Date() + " " + getEventReminder2Time() +"<br>"+
				"------------------------------------------<br>"
				);
	}
	
	private String putDateInProperFormat(String inDate) {
		// converts all dates to this format 'yyyy-MM-dd'
		String outDate = Time.getYearFromString(inDate) + "-"; // ex) '2017-' at this point		
		String eventMonth = Time.getMonthFromString(inDate,false);
		String eventDay = Time.getDayFromString(inDate);
		
		if (Integer.parseInt(eventMonth) < 10 && eventMonth.length()==1) { // ex) if month = 3, insert as 03. if month = 03, still add as 03
			outDate += "0" + eventMonth;
		} else {
			outDate += eventMonth;
		}
		
		outDate += "-"; // ex) '2017-07-' at this point

		if (Integer.parseInt(eventDay) <  10 && eventDay.length()==1) { // ex) if day = 3, insert as 03. if day = 03, still add as 03
			outDate += "0" + eventDay;
		} else {
			outDate += eventDay;
		}

		System.out.println(outDate);
		
		return outDate; // ex) '2017-07-03' at this point
	}
	

	
	// Getters
	public String getEventID() {
		return eventID;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public String getEventDate() {
		return eventDate;
	}
	public String getEventStartTime() {
		return eventStartTime;
	}
	public String getEventEndTime() {
		return eventEndTime;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public String getEventInvitees() {
		return eventInvitees;
	}
	public String getEventTag() {
		return eventTag;
	}
	public String getEventReminder1Date() {
		return eventReminder1Date;
	}
	public String getEventReminder1Time() {
		return eventReminder1Time;
	}
	public String getEventReminder2Date() {
		return eventReminder2Date;
	}
	public String getEventReminder2Time() {
		return eventReminder2Time;
	}
	public Date getEventStartDateTime() {
		Date date = null;
		try {
			date = new SimpleDateFormat("HH:mm yyyy-M-d").parse(this.getEventStartTime() + " " + this.getEventDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return date;
	}
	public Date getEventEndDateTime() {
		Date date = null;
		try {
			date = new SimpleDateFormat("HH:mm yyyy-M-d").parse(this.getEventEndTime() + " " + this.getEventDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return date;
	}
	
	// Setters
	public void setEventID(String id) {
		eventID = id;
	}
	public void setEventTitle(String title) {
		eventTitle = title;
	}
	public void setEventDescription(String description) {
		eventDescription = description;
	}
	public void setEventDate(String date) {
		eventDate = putDateInProperFormat(date);
	}
	public void setEventStartTime(String sTime) {
		eventStartTime = sTime;
	}
	public void setEventEndTime(String eTime) {
		eventEndTime = eTime;
	}
	public void setEventLocation(String location) {
		eventLocation =  location;
	}
	public void setEventInvitees(String invitees) {
		eventInvitees = invitees;
	}
	public void setEventTag(String tag) {
		eventTag =  tag;
	}
	public void setEventReminder1Date(String reminder) {
		eventReminder1Date = putDateInProperFormat(reminder);
	}
	public void setEventReminder1Time(String reminder) {
		eventReminder1Time = reminder;
	}
	public void setEventReminder2Date(String reminder) {
		eventReminder2Date = putDateInProperFormat(reminder);
	}
	public void setEventReminder2Time(String reminder) {
		eventReminder2Time = reminder;
	}
}