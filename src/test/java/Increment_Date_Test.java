package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import time.Time;

public class Increment_Date_Test {

	@Test
	public void test() {
		String testDate_Str = "2017-04-28";
		String testString = Time.incrementDate(testDate_Str);
		String answerString = "2017-05-05";
		System.out.println(testString);
		System.out.println(answerString);
		assertEquals(testString.equals(answerString), true);//should be true
	}

}
