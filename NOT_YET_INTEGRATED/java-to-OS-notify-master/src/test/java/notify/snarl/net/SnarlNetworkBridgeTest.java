package notify.snarl.net;

import org.junit.Ignore;
import org.junit.Test;

public class SnarlNetworkBridgeTest {

    @Test
    @Ignore("skip")
    public void testShowMessage() throws Exception {
	SnarlNetworkBridge.snRegisterConfigIfNecessary("Maven Progress", "localhost");
	SnarlNetworkBridge.snShowMessage("Maven Progress", "title", "content");
	SnarlNetworkBridge.snRevokeConfig();
    }

    @Test
    @Ignore("skip")
    public void testIcon() throws Exception {
	SnarlNetworkBridge.snRegisterConfigIfNecessary("Maven Progress Icon", "localhost");
	SnarlNetworkBridge.snShowMessage(new Notification("Maven Progress Icon", "title", "content", "C:\\Program Files (x86)\\full phat\\Snarl\\etc\\icons\\bad.png"));
	SnarlNetworkBridge.snRevokeConfig();

    }
}
