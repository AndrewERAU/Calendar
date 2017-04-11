package event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
		if (!"".equals(eventDate)) {
			this.eventDate = eventDate;
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
		if (!"".equals(eventReminder1Date)) {
			this.eventReminder1Date = eventReminder1Date;
		} else {
			this.eventReminder1Date = "NULL";
		}
		if (!"".equals(eventReminder1Time)) {
			this.eventReminder1Time = eventReminder1Time;
		} else {
			this.eventReminder1Time = "NULL";
		}
		if (!"".equals(eventReminder2Date)) {
			this.eventReminder2Date = eventReminder2Date;
		} else {
			this.eventReminder2Date = "NULL";
		}
		if (!"".equals(eventReminder2Time)) {
			this.eventReminder2Time = eventReminder2Time;
		} else {
			this.eventReminder2Time = "NULL";
		}
	}
	/* TODO: remove this
	public Event addSingleQuotes() {
		Event event = new Event();
		if (!"NULL".equals(this.eventTitle)) {
			event.setEventTitle("'" + this.eventTitle + "'");
		}
		
		if (!"NULL".equals(this.eventDescription)) {
			event.setEventDescription("'" + this.eventDescription + "'");
		}
		
		if (!"NULL".equals(this.eventDate)) {
			event.setEventDate("'" + this.eventDate + "'");
		}
		if (!"NULL".equals(this.eventStartTime)) {
			event.setEventStartTime("'" + this.eventStartTime + "'");
		}
		if (!"NULL".equals(this.eventEndTime)) {
			event.setEventEndTime("'" + this.eventEndTime + "'");
		}
		if (!"NULL".equals(this.eventLocation)) {
			event.setEventLocation("'" + this.eventLocation + "'");
		}
		if (!"NULL".equals(this.eventInvitees)) {
			event.setEventInvitees("'" + this.eventInvitees + "'");
		}
		if (!"NULL".equals(this.eventTag)) {
			event.setEventTag("'" + this.eventTag + "'");
		}
		if (!"NULL".equals(this.eventReminder1)) {
			event.setEventReminder1("'" + this.eventReminder1 + "'");
		}
		if (!"NULL".equals(this.eventReminder2)) {
			event.setEventReminder1("'" + this.eventReminder2 + "'");
		}

		return event;
	}
	*/
	
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
		eventDate = date;
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
		eventReminder1Date = reminder;
	}
	public void setEventReminder1Time(String reminder) {
		eventReminder1Time = reminder;
	}
	public void setEventReminder2Date(String reminder) {
		eventReminder2Date = reminder;
	}
	public void setEventReminder2Time(String reminder) {
		eventReminder2Time = reminder;
	}
}