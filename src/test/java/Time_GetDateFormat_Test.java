package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import time.Time;

public class Time_GetDateFormat_Test {

	@Test
	public void test() {
		assertEquals(Time.getDateFormat("2017-04-12"),"yyyy-MM-dd");
		assertEquals(Time.getDateFormat("2017-4-2"),"yyyy-M-d");
		assertEquals(Time.getDateFormat("2017-11-9"),"yyyy-MM-d");
		assertEquals(Time.getDateFormat("2017-4-12"),"yyyy-M-dd");
	}

}
