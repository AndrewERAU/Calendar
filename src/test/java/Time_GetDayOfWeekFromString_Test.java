package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import time.Time;

public class Time_GetDayOfWeekFromString_Test {

	@Test
	public void test() {
		assertEquals(Time.getWeekdayFromString("2017-4-9"),"Sunday");
		assertEquals(Time.getWeekdayFromString("2017-4-10"),"Monday");
		assertEquals(Time.getWeekdayFromString("2017-4-11"),"Tuesday");
		assertEquals(Time.getWeekdayFromString("2017-4-12"),"Wednesday");
		assertEquals(Time.getWeekdayFromString("2017-4-13"),"Thursday");
		assertEquals(Time.getWeekdayFromString("2017-4-14"),"Friday");
		assertEquals(Time.getWeekdayFromString("2017-4-15"),"Saturday");
	}

}
