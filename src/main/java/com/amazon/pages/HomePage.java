package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.amazon.actiondriver.ActionDriver;
import com.amazon.base.BaseClass;

public class HomePage {

	private ActionDriver actionDriver;

	// Define locators using By class
	private By allTab = By.xpath("(//span[text()='All'])[2]") ;
	private By userIDButton = By.xpath("//i[text()='your account']");
	private By logoutButton = By.xpath("//a[text()='Sign Out']");
	private By amazonLogo = By.className("nav-logo-base");
	
	private By searchBox = By.id("twotabsearchtextbox");
    private By searchButton = By.id("nav-search-submit-button");

	// Initialize the ActionDriver object by passing WebDriver instance
	/* public HomePage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver) ;
	} */
	
	public HomePage(WebDriver driver) {
		this.actionDriver = BaseClass.getActionDriver() ;
	}
	
	//Method to verify if Amazon text is visible
	public boolean isAllTabVisible() {
		return actionDriver.isDisplayed(allTab) ;
	}
	
	public boolean verifyAmazonLogo() {
		return actionDriver.isDisplayed(amazonLogo) ;
	}
	
	//Method to perform logout operation
	public void logout() {
		actionDriver.click(userIDButton);
		actionDriver.click(logoutButton);
	}
	
	//Method to search for product
	public void searchForProduct(String productName) {
        WebElement element = actionDriver.findElement(searchBox);
        element.clear();
        element.sendKeys(productName);
        actionDriver.click(searchButton);
    }

}
