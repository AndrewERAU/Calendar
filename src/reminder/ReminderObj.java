package reminder;

import time.Time;

public class ReminderObj {
	// TODO: rename this to Reminder
	private String dayOfWeek;
	private String month;
	private String day;
	private String time;
	private String eventTitle;
	
	public ReminderObj (String date, String time, String title) {
		this.dayOfWeek = Time.getWeekdayFromString(date,true); // true means convert weekday to word.  ex) "Sunday", not "0"
		this.month = Time.getMonthFromString(date,true); // true means convert month to word.  ex) "January", not "1"
		this.day = Time.getDayFromString(date);
		this.time = Time.getCivilianTimeFromString(time);
		this.eventTitle = title;	
	}
	
	// Getters
	public String getReminderDayOfWeek() {
		return dayOfWeek;
	}
	public String getReminderMonth() {
		return month;
	}
	public String getReminderDay() {
		return day;
	}
	public String getReminderTime() {
		return time;
	}
	public String getReminderEventTitle() {
		return eventTitle;
	}


}
