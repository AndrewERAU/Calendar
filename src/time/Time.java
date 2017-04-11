package time;

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
		int month = Integer.parseInt(currentDate_Str.substring(5, 7));
		int day = Integer.parseInt(currentDate_Str.substring(8, 10));
		int numberOfDaysInMonth = numberOfDaysInMonth();
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
	
}
