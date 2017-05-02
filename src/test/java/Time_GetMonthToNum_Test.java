package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import time.Time;

public class Time_GetMonthToNum_Test {

	@Test
	public void test() {
		assertEquals(Time.getMonthToNum("January"),"01");
		assertEquals(Time.getMonthToNum("February"),"02");
		assertEquals(Time.getMonthToNum("March"),"03");
		assertEquals(Time.getMonthToNum("April"),"04");
		assertEquals(Time.getMonthToNum("May"),"05");
		assertEquals(Time.getMonthToNum("June"),"06");
		assertEquals(Time.getMonthToNum("July"),"07");
		assertEquals(Time.getMonthToNum("August"),"08");
		assertEquals(Time.getMonthToNum("September"),"09");
		assertEquals(Time.getMonthToNum("October"),"10");
		assertEquals(Time.getMonthToNum("November"),"11");
		assertEquals(Time.getMonthToNum("December"),"12");
	}

}
