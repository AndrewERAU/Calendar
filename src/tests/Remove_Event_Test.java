package tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import database.DatabaseMgr;
import event.Event;

public class Remove_Event_Test {
	@Test
	public void test() {
		File dbFile = new File(DatabaseMgr.TEST_DB_PATH);
		dbFile.delete();
		DatabaseMgr db = new DatabaseMgr();
		assertEquals(db.retrieveEvents('I',"1").size(),0);
		db.insertEvent(new Event("Meeting","NULL","2017-02-28","NULL","NULL","NULL","NULL","NULL","NULL","NULL"));
		assertEquals(db.retrieveEvents('I',"1").size(),1);
		db.removeEvent(1);
		assertEquals(db.retrieveEvents('I',"1").size(),0);
		dbFile.delete();
	}
}
