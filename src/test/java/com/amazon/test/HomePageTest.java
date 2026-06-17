package com.amazon.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.base.BaseClass;
import com.amazon.pages.HomePage;
import com.amazon.pages.LoginPage;

public class HomePageTest extends BaseClass{
	
	private LoginPage loginPage ;
	private HomePage homePage ;
	
	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(getDriver()) ;
		homePage = new HomePage(getDriver()) ;
	}
	
	@Test
	public void verifyAmazonLogo() {
		loginPage.login("amazonproject44@gmail.com", "randomPass");
		Assert.assertTrue(homePage.verifyAmazonLogo(), "Logo is not visible");
	}

}
