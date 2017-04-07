package reminder;

import java.util.Calendar;
import java.util.Date;

public class Event {
	private String eventID;
	private String eventTitle;
	private String eventDescription;
	private Date eventStart;
	private Date eventEnd;
	private String eventLocation;
	private String eventInvitees;
	private String eventTag;
	
	public Event() {
		this.eventID = null;
		this.eventTitle = null;
		this.eventDescription = null;
		this.eventStart = null;
		this.eventEnd = null;
		this.eventLocation = null;
		this.eventInvitees = null;
		this.eventTag = null;
	}
	
	public Event(String eventTitle,
			  String eventDescription,
			  Date eventStart,
			  Date eventEnd,
			  String eventLocation,
			  String eventInvitees,
			  String eventTag) {
		
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
		if (!this.eventStart.equals(null)) {
			this.eventStart = eventStart;
		} /*else {
			this.event = "NULL";
		}*/
		if (!this.eventEnd.equals(null)) {
			this.eventEnd = eventEnd;
		} /*else {
			this.eventStartTime = "NULL";
		}*/
		/*if (!"".equals(eventEndTime)) {
			this.eventEndTime = eventEndTime;
		} else {
			this.eventEndTime = "NULL";
		}*/
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
	/*	if (!"".equals(eventReminder1)) {
			this.eventReminder1 = eventReminder1;
		} else {
			this.eventReminder1 = "NULL";
		}
		if (!"".equals(eventReminder2)) {
			this.eventReminder2 = eventReminder2;
		} else {
			this.eventReminder2 = "NULL";
		}*/
	}
	
	public Event addSingleQuotes() {
		Event event = new Event();
		if (!"NULL".equals(this.eventTitle)) {
			event.setEventTitle("'" + this.eventTitle + "'");
		}
		
		if (!"NULL".equals(this.eventDescription)) {
			event.setEventDescription("'" + this.eventDescription + "'");
		}
//TODO: Verify whether this next two if clauses need to remain in the code
		/*if (!this.eventStart.equals(null)) {
			event.setEventStartTime(this.eventStart);
		}
		if (!this.eventEnd.equals(null)) {
			event.setEventEndTime(this.eventEnd);
		}*/
		/*if (!"NULL".equals(this.eventEndTime)) {
			event.setEventEndTime("'" + this.eventEndTime + "'");
		}*/
		if (!"NULL".equals(this.eventLocation)) {
			event.setEventLocation("'" + this.eventLocation + "'");
		}
		if (!"NULL".equals(this.eventInvitees)) {
			event.setEventInvitees("'" + this.eventInvitees + "'");
		}
		if (!"NULL".equals(this.eventTag)) {
			event.setEventTag("'" + this.eventTag + "'");
		}
		return event;
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
	public Date getEventStartTime() {
		return eventStart;
	}
	public Date getEventEndTime() {
		return eventEnd;
	}
	/*public String getEventEndTime() {
		return eventEndTime;
	}*/
	public String getEventLocation() {
		return eventLocation;
	}
	public String getEventInvitees() {
		return eventInvitees;
	}
	public String getEventTag() {
		return eventTag;
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
	/*public void setEventDate(String date) {
		eventDate = date;
	}*/
	public void setEventStartTime(Integer month, Integer date, 
								  Integer year, Integer hour,
								  Integer minute) {
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, date);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
		this.eventStart = cal.getTime();
	}
	public void setEventEndTime(Integer month, Integer date, 
								Integer year, Integer hour, 
								Integer minute) {
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, date);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
		this.eventEnd = cal.getTime();
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
}
