package event;

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
	private String eventReminder1;
	private String eventReminder2;
	
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
		this.eventReminder1 = null;
		this.eventReminder2 = null;
	}
	
	public Event(String eventTitle,
			  String eventDescription,
			  String eventDate,
			  String eventStartTime,
			  String eventEndTime,
			  String eventLocation,
			  String eventInvitees,
			  String eventTag,
			  String eventReminder1,
			  String eventReminder2) {
		
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
		if (!"".equals(eventReminder1)) {
			this.eventReminder1 = eventReminder1;
		} else {
			this.eventReminder1 = "NULL";
		}
		if (!"".equals(eventReminder2)) {
			this.eventReminder2 = eventReminder2;
		} else {
			this.eventReminder2 = "NULL";
		}
	}
	
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
	public String getEventReminder1() {
		return eventReminder1;
	}
	public String getEventReminder2() {
		return eventReminder2;
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
	public void setEventReminder1(String reminder) {
		eventReminder1 = reminder;
	}
	public void setEventReminder2(String reminder) {
		eventReminder2 = reminder;
	}
}
