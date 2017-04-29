package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import event.Event;

public class Event_Format_Event_Test {

	private Event event = new Event("track", // eventTitle
			"Run a lot.", // eventDescription
			"2017-4-20", // eventDate
			"04:00", // eventStartTime
			"19:00", // eventEndTime
			"Willow Creek", // eventLocation
			"johndoe@example.com", // eventInvitees
			"run, workout, improve", // eventTag
			"2017-4-20",
			"4:00",
			"2017-4-19",
			"14:00");
	
	@Test
	public void test() {
		
		assertEquals(event.formatEvent(),("<b>Thursday, April 20 - 4:00am-7:00pm:<br></b>"+
	"<b><u>Title:</u></b> track<br>"+
	"<b><u>Description:</u></b> Run a lot.<br>"+
	"<b><u>Tag:</u></b> run, workout, improve<br>"+
	"<b><u>Location:</u></b> Willow Creek<br>"+
	"<b><u>Invitees:</u></b> johndoe@example.com<br>"+
	"<b><u>Reminder 1:</u></b> 2017-04-20 4:00am<br>"+
	"<b><u>Reminder 2:</u></b> 2017-04-19 2:00pm<br>"+
	"------------------------------------------<br>"));
	}

}

