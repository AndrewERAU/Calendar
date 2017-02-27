package tests;

import static org.junit.Assert.*;

import java.io.File;

import file_io.ICS_File;

import org.junit.Test;

import event.Event;
//import file_io.ICS_File;

public class Ics_Export_Test {
	private final String filePath = "./tmp/icsExport.ics";
	private Event event = new Event("Meeting", // eventTitle
			"Meeting to go over plan details.", // eventDescription
			"2017-02-28", // eventDate
			"12:30:00", // eventStartTime
			"13:30:00", // eventEndTime
			"3500 Deer Creek Rd, Palo Alto, CA 94304", // eventLocation
			"", // eventInvitees
			"Work", // eventTag
			"",
			"");

	@Test
	public void test() {
		// TODO: This will fail if ./tmp does not exist
		ICS_File icsFile = new ICS_File(filePath,event);
		File file = new File(filePath);
		file.delete();
		assertTrue(!file.isFile()); // should be no file to begin with
		icsFile.IcsExport(); // creates the ics file
		assertTrue(file.isFile()); // file should exist now
		// TODO: Should validate that file is in correct .ics format
		file.delete();
	}

}
