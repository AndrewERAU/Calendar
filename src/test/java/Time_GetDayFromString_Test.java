package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import time.Time;

public class Time_GetDayFromString_Test {

	@Test
	public void test() {
		assertEquals(Time.getDayFromString("2017-4-11"),"11");
		assertEquals(Time.getDayFromString("2017-4-1"),"1");
		assertEquals(Time.getDayFromString("1980-12-02"),"02");
		assertEquals(Time.getDayFromString("1999-09-03"),"03");
	}

}
