package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import time.Time;

public class Time_GetDayOfWeekFromString_Test {

	@Test
	public void test() {
		assertEquals(Time.getWeekdayFromString("2017-4-9",true),"Sunday"); // true means convert weekday to word (instead of leaving it as number)
		assertEquals(Time.getWeekdayFromString("2017-4-10",true),"Monday");
		assertEquals(Time.getWeekdayFromString("2017-4-11",true),"Tuesday");
		assertEquals(Time.getWeekdayFromString("2017-4-12",true),"Wednesday");
		assertEquals(Time.getWeekdayFromString("2017-4-13",true),"Thursday");
		assertEquals(Time.getWeekdayFromString("2017-4-14",true),"Friday");
		assertEquals(Time.getWeekdayFromString("2017-4-15",true),"Saturday");
	}

}
