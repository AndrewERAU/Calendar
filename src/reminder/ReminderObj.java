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
		this.dayOfWeek = Time.getWeekdayFromString(date);
		this.month = Time.getMonthFromString(date);;
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
