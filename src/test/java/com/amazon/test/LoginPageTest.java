package com.amazon.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.base.BaseClass;
import com.amazon.pages.HomePage;
import com.amazon.pages.LoginPage;

public class LoginPageTest extends BaseClass {
	
	private LoginPage loginPage ;
	private HomePage homePage ;
	
	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(getDriver()) ;
		homePage = new HomePage(getDriver()) ;
	}
	
	@Test
	public void verifyValidLoginTest() {
		loginPage.login("amazonproject44@gmail.com", "randomPass");
		Assert.assertTrue(homePage.isAllTabVisible(), "Amazon text should be visible after successful login");
		homePage.logout();
		staticWait(2) ;
	}
	
	@Test
	public void invalidLoginTest() {
		loginPage.login("amazonproject44@gmail.com", "randomPass1");
		String expectedErrorMessage = "Your password is incorrect1" ;
		Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorMessage), "Test Failed; Invalid Error Message");
	}

}
