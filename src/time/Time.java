package time;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
	
	public static String getCurrentDay(){
		int day;
		String dayString = "";
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		day = localDate.getDayOfMonth();
		
		switch (day){
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
	
	
}
