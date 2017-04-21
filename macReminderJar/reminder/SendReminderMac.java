package reminder;

import notify.MessageType;
import notify.Notify; // TODO: remove this, do i need it?
import notify.macosxcenter.MacOsXNotifier;

//3rdParty/terminal-notifier-1.7.1/terminalfier.app/Contents/MacOS/terminal-notifier -title Reminder  -message "Time to workout." -actions Close
public class SendReminderMac {
	public static void main(String[] args) {
		
		MacOsXNotifier notifier = new MacOsXNotifier();
		if (args.length == 2) {
			notifier.notify(MessageType.NONE,args[0],args[1]);
		    return;
		}
		
		System.out.println("usage: java -cp MacReminderJarVERSION.jar reminder.SendReminderMac title message");
		return;
	}
}
