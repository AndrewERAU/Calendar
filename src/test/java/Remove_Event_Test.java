package test.java;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import database.DatabaseMgr;
import event.Event;

public class Remove_Event_Test {
	Event testEvent = new Event("Meeting","NULL","2017-02-28","NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL");
	
	@Test
	public void test() {
		File dbFile = new File(DatabaseMgr.DB_PATH);
		dbFile.delete();
		DatabaseMgr db = new DatabaseMgr();
		assertEquals(db.retrieveEvents('A',"").size(),0); // Make sure there are no events in db
		assertEquals(db.retrieveEvents('I',"1").size(),0); // Make sure there are no events in db
		
		testEvent = db.insertEvent(testEvent); //  Insert event
		assertEquals(db.retrieveEvents('I',testEvent.getEventID()).size(),1); // Make sure the event with the id we just inserted is in db
		db.removeEvent(Integer.parseInt(testEvent.getEventID())); // remove the event by id
		assertEquals(db.retrieveEvents('I',testEvent.getEventID()).size(),0); // make sure event is no longer in db
		dbFile.delete();
	}
}



