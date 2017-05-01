package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import time.Time;

public class Increment_Date_Test {

	@Test
	public void test() {
		//String testString = Time.incrementDate("2017-04-28"); see issue #48 https://github.com/AndrewERAU/Calendar/issues/48
		//assertEquals(testString,"2017-05-05");//should be true // once that issue is fixed, uncomment this
		
		String testString2 = Time.incrementDate("2017-04-10");
		assertEquals(testString2,"2017-04-17");
		
	}

}
