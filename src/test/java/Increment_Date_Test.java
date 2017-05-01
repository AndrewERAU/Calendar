package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import time.Time;

public class Increment_Date_Test {

	@Test
	public void test() {
		String testString = Time.incrementDate("2017-04-28");
		assertEquals(testString,"2017-05-05");//should be true
	}

}
