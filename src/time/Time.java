package time;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Time {

	
	
	public static String getMonthYear(){
		int year; 
		int month; 
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		year = localDate.getYear();
		month =localDate.getMonthValue();
		String output = Integer.toString(month) + ", " + Integer.toString(year);
		return output;
	}
	
	
}
