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
		case 2:
			monthString = "February";
		case 3:
			monthString = "March";
		case 4:
			monthString = "April";
		case 5:
			monthString = "May";
		case 6:
			monthString = "June";
		case 7:
			monthString = "July";
		case 8:
			monthString = "August";
		case 9:
			monthString = "September";
		case 10:
			monthString = "October";
		case 11:
			monthString = "November";
		case 12:
			monthString = "Decmeber";
		}
		
		String output = monthString + ", " + Integer.toString(year);
		return output;
	}
	
	
}
