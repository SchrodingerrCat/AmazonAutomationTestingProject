package com.amazon.test;

import org.testng.annotations.Test;

import com.amazon.base.BaseClass;

public class DummyClass extends BaseClass{
	
	@Test
	public void dummyTest() {
		String title = getDriver().getTitle();
		assert title.equals("Amazon Sign-In") : "Test Failed - Title is Not Matching" ;
		
		System.out.println("Test Passed - Title is Matching");
		
		
	}

}
