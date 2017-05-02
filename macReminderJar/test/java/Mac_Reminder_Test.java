package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import notify.macosxcenter.MacOsXNotifier;
import reminder.SendReminderMac;
import notify.MessageType;

//import reminder.SendReminderMac;

public class Mac_Reminder_Test {

	@Test
	public void test() {

		MacOsXNotifier notifier = new MacOsXNotifier();
		assert(notifier.isSupported() == true);
		//SendReminderMac.main(null); // TODO: MAC REMINDERS
	}

}
