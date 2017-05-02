package notify;

import org.junit.Ignore;
import org.junit.Test;

public class NotifyTest {

	@Test
	@Ignore("skip")
	public void testNotifyMessageTypeStringString() {
		Notify.getInstance().notify(MessageType.NONE, "NONE", "none");
		Notify.getInstance().notify(MessageType.INFO, "INFO", "info");
		Notify.getInstance().notify(MessageType.WARNING, "WARNING", "warning");
		Notify.getInstance().notify(MessageType.ERROR, "ERROR", "error");
	}

}
