package notify.macosxcenter;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;

import notify.MessageType;
import org.junit.Test;

/**
 * @author francois wauquier
 */
public class MacOsXNotifierTest {

	MacOsXNotifier macOsXNotifier = new MacOsXNotifier();

    @Test
    @Ignore("skip")
	public void testIsSupported() throws Exception {
		assertTrue(macOsXNotifier.isSupported());
	}

    @Test
    //@Ignore("skip")
	public void testNotify() throws Exception {
		//macOsXNotifier.notify(MessageType.NONE, "NONE", "none");
		//Thread.sleep(2000);
		//macOsXNotifier.notify(MessageType.INFO, "INFO", "info");
		//Thread.sleep(2000);
		//macOsXNotifier.notify(MessageType.WARNING, "WARNING", "warning");
		//Thread.sleep(2000);
		//macOsXNotifier.notify(MessageType.ERROR, "ERROR", "error");
		//Thread.sleep(2000);
    	macOsXNotifier.notify(MessageType.NONE, "Reminder", "Buy stuff");
	}
}
