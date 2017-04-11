package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import time.Time;

public class Time_GetMonthFromString_Test {

	@Test
	public void test() {
		assertEquals(Time.getMonthFromString("2017-1-14"),"January");
		assertEquals(Time.getMonthFromString("2017-2-14"),"February");
		assertEquals(Time.getMonthFromString("2017-3-14"),"March");
		assertEquals(Time.getMonthFromString("2017-4-14"),"April");
		assertEquals(Time.getMonthFromString("2017-5-14"),"May");
		assertEquals(Time.getMonthFromString("2017-6-14"),"June");
		assertEquals(Time.getMonthFromString("2017-7-14"),"July");
		assertEquals(Time.getMonthFromString("2017-8-14"),"August");
		assertEquals(Time.getMonthFromString("2017-9-14"),"September");
		assertEquals(Time.getMonthFromString("2017-10-14"),"October");
		assertEquals(Time.getMonthFromString("2017-11-14"),"November");
		assertEquals(Time.getMonthFromString("2017-12-14"),"December");
		
		assertEquals(Time.getMonthFromString("2017-01-14"),"January");
	}

}
