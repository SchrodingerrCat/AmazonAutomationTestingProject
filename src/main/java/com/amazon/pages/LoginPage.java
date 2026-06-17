package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.amazon.actiondriver.ActionDriver;
import com.amazon.base.BaseClass;

public class LoginPage extends BaseClass {
	
	private ActionDriver actionDriver ;
	
	//Define locators using By class
	
	private By userNameField = By.name("email") ;
	private By passwordField = By.cssSelector("input[type='password']") ;
	private By submitButton = By.cssSelector("input[type='submit']") ;
	private By errorMessage = By.xpath("//div[contains(text(),'Your password is incorrect')]") ;
	
	//Initialize the ActionDriver object by passing WebDriver instance
	public LoginPage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver) ;
	}
	
	//Method to perform login
	public void login(String userName, String password) {
		actionDriver.enterText(userNameField, userName);
		actionDriver.click(submitButton);
		
		actionDriver.enterText(passwordField, password);
		actionDriver.click(submitButton);
	}
	
	//Method to click if error message is displayed
	public boolean isErrorMessageDisplayed() {
		return actionDriver.isDisplayed(errorMessage) ;
	}
	
	//Method to get the text from Error message
	public String getErrorMessageText() {
		return actionDriver.getText(errorMessage) ;
	}
	
	//Verify if error is correct or not
	public void verifyErrorMessage(String expectedError) {
		actionDriver.compareText(errorMessage, expectedError);
	}
}
