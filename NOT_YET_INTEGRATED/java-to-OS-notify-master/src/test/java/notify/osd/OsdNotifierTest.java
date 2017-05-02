package notify.osd;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;

import notify.MessageType;

import org.junit.Test;

public class OsdNotifierTest {

	OsdNotifier osdNotifier = new OsdNotifier();

	@Test
	@Ignore("skip")
	public void testIsSupported() {
		assertTrue(osdNotifier.isSupported());
	}

	@Test
	@Ignore("skip")
	public void testNotifyMessageTypeStringString() throws Exception {
		osdNotifier.notify(MessageType.NONE, "NONE", "none");
		Thread.sleep(1000);
		osdNotifier.notify(MessageType.INFO, "INFO", "info");
		Thread.sleep(1000);
		osdNotifier.notify(MessageType.WARNING, "WARNING", "warning");
		Thread.sleep(1000);
		osdNotifier.notify(MessageType.ERROR, "ERROR", "error");
		Thread.sleep(1000);
	}

}
