package reminder;

import time.Time;

public class CronReminder {

	// All these fields will be the integer corresponding to thier value
	// ex) month = 1 (which is January)
	private String minute;
	private String hour;
	private String dayOfMonth;
	private String month;
	private String dayOfWeek;
	private String eventTitle;
	
	public CronReminder (String date, String time, String title) {
		this.dayOfWeek = Time.getWeekdayFromString(date,false); // false means leave as int.  ex) returns "0" instead of "Sunday"
		this.month = Time.getMonthFromString(date,false); // false means leave as int.  ex) returns "1" instead of "January"
		this.dayOfMonth = Time.getDayFromString(date);
		this.hour = Time.getHourFromString(time);
		this.minute = Time.getMinuteFromString(time);
		this.eventTitle = title;	
	}
	
	// Getters
	public String getReminderWeekday() {
		return dayOfWeek;
	}
	public String getReminderMonth() {
		return this.month;
	}
	public String getReminderDay() {
		return dayOfMonth;
	}
	public String getReminderHour() {
		return hour;
	}
	public String getReminderMinute() {
		return minute;
	}
	public String getReminderTitle() {
		return eventTitle;
	}
	
	
	
//	public String getReminderDayOfWeek() {
//		return dayOfWeek;
//	}
//	public String getReminderMonth() {
//		return month;
//	}
//	public String getReminderDay() {
//		return day;
//	}
//	public String getReminderTime() {
//		return time;
//	}
//	public String getReminderEventTitle() {
//		return eventTitle;
//	}


}
