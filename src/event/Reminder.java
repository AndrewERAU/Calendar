package event;

public class Reminder {
	// This class will handle sending events to server
	private String eventTitle = null;
	private String eventInfo = null;
	private Integer alertTime = null;
	
	public Reminder() {
		eventTitle = "";
		eventInfo = "";
		alertTime = -1;
	}
	
	public void getReminder() {
		System.out.println("getReminder() called");
	}
	
	public void setReminder() {
		System.out.println("setReminder() called");
	}
}
