package time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Time {

	
	
	public static String getMonthYear(){
		int year; 
		int month;
		String monthString = "";
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		year = localDate.getYear();
		month =localDate.getMonthValue();
		
		switch (month){
		case 1:
			monthString = "January";
			break;
		case 2:
			monthString = "February";
			break;
		case 3:
			monthString = "March";
			break;
		case 4:
			monthString = "April";
			break;
		case 5:
			monthString = "May";
			break;
		case 6:
			monthString = "June";
			break;
		case 7:
			monthString = "July";
			break;
		case 8:
			monthString = "August";
			break;
		case 9:
			monthString = "September";
			break;
		case 10:
			monthString = "October";
			break;
		case 11:
			monthString = "November";
			break;
		case 12:
			monthString = "Decmeber";
			break;
		}
		
		String output = monthString + ", " + Integer.toString(year);
		return output;
	}
	
	
	public static int getCurrentDay(){
		//GET CURRENT DAY NUMBER
		int day;
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		day = localDate.getDayOfMonth();
		
		return day;
	}
	
	
	public static int getFirstDay(){
		
		int day;
		int dayNumber = getCurrentDay();
		int dayDifference = dayNumber - 1;
		
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		day = localDate.getDayOfWeek().getValue() - dayDifference;
		while(day < 0){
			day = day + 7;
		}
		day = day + 1;
		
		return day;
	}
	
	public static int numberOfDaysInMonth(){
		
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month =localDate.getMonthValue();
		int year = localDate.getYear();
		
		int numberOfDays = 0;
		
		if((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)){
			numberOfDays = 31;
		} else if((month == 4) || (month == 6) || (month == 9) || (month == 11)){
			numberOfDays = 30;
		} else {
			numberOfDays = 28;
			if(year % 4 == 0){
				numberOfDays = 29;
			}
			if(year % 100 == 0){
				numberOfDays = 28;
			}
			if(year % 400 == 0){
				numberOfDays = 29;
			}
		}
		
		return numberOfDays;
	}
	
	
	public static String incrementDate(String currentDate_Str){
		
		int year = Integer.parseInt(currentDate_Str.substring(0, 4));
		int month; //= Integer.parseInt(currentDate_Str.substring(5, 7));
		int day; //= Integer.parseInt(currentDate_Str.substring(8, 10));
		int numberOfDaysInMonth = numberOfDaysInMonth();

		
		//2017-4-11
		if (currentDate_Str.charAt(6) == '-') {
			month = Integer.parseInt(currentDate_Str.substring(5, 6));
		} else {
			month = Integer.parseInt(currentDate_Str.substring(5, 7));
		}

		day = Integer.parseInt(getDayFromString(currentDate_Str));
		
		int adjustedYear = year;
		int adjustedMonth = month;
		int adjustedDay = day;
		String output = "";
		
		

		
		adjustedDay = adjustedDay + 7;
		if(adjustedDay > numberOfDaysInMonth){
			adjustedDay = adjustedDay - numberOfDaysInMonth;
			adjustedMonth = adjustedMonth + 1;
			if(adjustedMonth > 12){
				adjustedMonth = 1;
				adjustedYear = adjustedYear + 1;
			}
		}
		
		if((adjustedMonth < 10) && (adjustedDay < 10)){
			output = Integer.toString(adjustedYear) + "-0" + Integer.toString(adjustedMonth) + "-0" + Integer.toString(adjustedDay);
		} else if(adjustedMonth < 10){
			output = Integer.toString(adjustedYear) + "-0" + Integer.toString(adjustedMonth) + "-" + Integer.toString(adjustedDay);
		} else if(adjustedDay < 10){
			output = Integer.toString(adjustedYear) + "-" + Integer.toString(adjustedMonth) + "-0" + Integer.toString(adjustedDay);
		} else {
			output = Integer.toString(adjustedYear) + "-" + Integer.toString(adjustedMonth) + "-" + Integer.toString(adjustedDay);
		}
		
		return output;
	}
	
	public static String getCivilianTimeFromString(String militaryTime) {
		String civilianTime = null;
		String ampm = null;
		int militaryHour = 0;
		String militaryMinutes;
		// military time format is 01:20, 13:40, etc.
		
		// Next if does - ex) if 8:00, convert to 08:00
		if (militaryTime.charAt(1) == ':') {
			militaryTime = '0' + militaryTime;
		}
		
		militaryHour = Integer.parseInt(militaryTime.substring(0, 2));
		if (militaryHour <= 12) {
			ampm = "am";
			civilianTime = Integer.toString(militaryHour) + ':';
		} else {
			ampm = "pm";
			civilianTime = Integer.toString(militaryHour-12) + ':';
		}
		
		militaryMinutes = militaryTime.substring(3, 5);	
		civilianTime += militaryMinutes;
		civilianTime += ampm;
		
		return civilianTime;
	}
	
	public static String getDayFromString(String date) {
		/*
		 * Date is of format 2017-12-31, or 2017-4-2
		 * This function gets all the text after last dash, which is the day of the month
		 */
		int index = 0;
		char currentChar;
		int dashesFound = 0;
		
		do  {
			currentChar = date.charAt(index++);
			if (currentChar == '-') {
				dashesFound++;
			}
		} while (dashesFound < 2);
		
		
		return date.substring(index);
	}
	
	public static String getYearFromString(String date) {
		/*
		 * Date is of format 2017-12-31, or 2017-4-2
		 * This function gets all the text before the first dash, which is the year
		 */
	
		return date.substring(0,date.indexOf('-'));
	}
	
	public static String getWeekdayFromString(String date, boolean convertToWord) {
		/*
		 * Date is of format 2017-12-31, or 2017-4-2
		 * This function gets:
		 * 	- The day of the month
		 * 	- The month
		 * 	- The year
		 * And returns a string of the day of the week, either
		 * "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
		 */
		
		
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(new SimpleDateFormat(getDateFormat(date)).parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		
		if (convertToWord) {
			return dayOfWeekNumberToString(dayOfWeek);
		}
		
		return Integer.toString(dayOfWeek - 1); // -1 b/c crontab uses 0-6 for sun-sat, as is it is 1-6 = sun-sat
		
	}
	
	private static String dayOfWeekNumberToString(int inDay) {
		String dayString = null;
		switch(inDay) {
		case 1:
			dayString = "Sunday";
			break;
		case 2:
			dayString = "Monday";
			break;
		case 3:
			dayString = "Tuesday";
			break;
		case 4:
			dayString = "Wednesday";
			break;
		case 5:
			dayString = "Thursday";
			break;
		case 6:
			dayString = "Friday";
			break;
		case 7:
			dayString = "Saturday";
			break;
		}
		
		return dayString;
	}
	
	public static String getMonthFromString(String date,boolean convertToMonthName) {
		int monthInt = -1;
		
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(new SimpleDateFormat(getDateFormat(date)).parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		monthInt = c.get(Calendar.MONTH);
		
		if (convertToMonthName) {
			return monthIntToString(monthInt);		
		}
		
		return Integer.toString(monthInt+1); // +1 b/c 0-11 = jan-dec, but I want 1-12
	}
	
	private static String monthIntToString(int monthInt) {
		String monthStr = null;
		
		switch (monthInt) {
		case Calendar.JANUARY:
			monthStr = "January";
			break;
		case Calendar.FEBRUARY:
			monthStr = "February";
			break;
		case Calendar.MARCH:
			monthStr = "March";
			break;
		case Calendar.APRIL:
			monthStr = "April";
			break;
		case Calendar.MAY:
			monthStr = "May";
			break;
		case Calendar.JUNE:
			monthStr = "June";
			break;
		case Calendar.JULY:
			monthStr = "July";
			break;
		case Calendar.AUGUST:
			monthStr = "August";
			break;
		case Calendar.SEPTEMBER:
			monthStr = "September";
			break;
		case Calendar.OCTOBER:
			monthStr = "October";
			break;
		case Calendar.NOVEMBER:
			monthStr = "November";
			break;
		case Calendar.DECEMBER:
			monthStr = "December";
			break;
		}
		
		return monthStr;
	}
	
	public static String getCurrentDayInSqlFormat() {
		String todayString = null;
		
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		int month =localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		String yearStr = Integer.toString(year);
		String monthStr = null;
		String dayStr = null;
		
		// Keep 2 digits in date format.  ex) 2017-04-09. NOT 2017-4-9
		if (month < 10) {
			monthStr = "0" + Integer.toString(month);
		} else {
			monthStr = Integer.toString(month);
		}
		
		if (day < 10) {
			dayStr = "0" + Integer.toString(day);
		} else {
			dayStr = Integer.toString(day);
		}
		
		todayString = yearStr + '-' + monthStr + '-' + dayStr; // ex) 2017-11-24
		
		return todayString;
	}
	
	public static String getMinuteFromString(String inTime) {
		return inTime.substring(inTime.indexOf(':') + 1); // ex) "5:30" returns "30"
	}
	
	public static String getHourFromString(String inTime) {
		return inTime.substring(0,inTime.indexOf(':')); // ex) "5:30" returns "5"
	}
	
	public static String getDateFormat(String inDate) {
		String yearFormat = ""; // assumed the year is 4 digits
		String monthFormat = "";
		String dayFormat = "";
		
		int index = 0;
		char currentChar;
		
		currentChar = inDate.charAt(index);
		while (currentChar != '-') {
			yearFormat += "y";
			currentChar = inDate.charAt(++index);
		}
		
		currentChar = inDate.charAt(++index);
		while (currentChar != '-') {
			monthFormat += "M";
			currentChar = inDate.charAt(++index);
		}


		currentChar = inDate.charAt(index);
		while (index+1 < inDate.length()) {
			dayFormat += "d";
			currentChar = inDate.charAt(++index);
		}
		
		return yearFormat + "-" + monthFormat + "-" + dayFormat;//"yyyy-M-d";
	}



public static String getMonthToNum(String month_str){
	String month_num = "00";
	
	
	switch (month_str){
	case "January":
		month_num = "01";
		break;
	case "February":
		month_num = "02";
		break;
	case "March":
		month_num = "03";
		break;
	case "April":
		month_num = "04";
		break;
	case "May":
		month_num = "05";
		break;
	case "June":
		month_num = "06";
		break;
	case "July":
		month_num = "07";
		break;
	case "August":
		month_num = "08";
		break;
	case "September":
		month_num = "09";
		break;
	case "October":
		month_num = "10";
		break;
	case "November":
		month_num = "11";
		break;
	case "December":
		month_num = "12";
		break;
	}
	
	return month_num;
}



}

