package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import time.Time;

public class Time_GetMonthFromString_Test {

	@Test
	public void test() {
		assertEquals(Time.getMonthFromString("2017-1-14",true),"January");
		assertEquals(Time.getMonthFromString("2017-2-14",true),"February");
		assertEquals(Time.getMonthFromString("2017-3-14",true),"March");
		assertEquals(Time.getMonthFromString("2017-4-14",true),"April");
		assertEquals(Time.getMonthFromString("2017-5-14",true),"May");
		assertEquals(Time.getMonthFromString("2017-6-14",true),"June");
		assertEquals(Time.getMonthFromString("2017-7-14",true),"July");
		assertEquals(Time.getMonthFromString("2017-8-14",true),"August");
		assertEquals(Time.getMonthFromString("2017-9-14",true),"September");
		assertEquals(Time.getMonthFromString("2017-10-14",true),"October");
		assertEquals(Time.getMonthFromString("2017-11-14",true),"November");
		assertEquals(Time.getMonthFromString("2017-12-14",true),"December");
		
		assertEquals(Time.getMonthFromString("2017-01-14",true),"January");
		assertEquals(Time.getMonthFromString("1999-01-1",true),"January");
		assertEquals(Time.getMonthFromString("2129-01-01",true),"January");
	}

}


