package com.amazon.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionDriver {
	
	private WebDriver driver ;
	private WebDriverWait wait;
	
	public ActionDriver(WebDriver driver) {
		this.driver = driver ;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	//Method to click an element
	public void click(By by) {
		try {
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println("Unable to click the element: "+e.getMessage());
		}
	}
	
	//Wait for element to be clickable
	public void waitForElementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by)) ;
		} catch (Exception e) {
			System.out.println("element is not clickable: "+e.getMessage());
		}
	}
	
	//Wait for element to be visible
	public void waitForElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by)) ;
		} catch (Exception e) {
			System.out.println("Element is not visible: "+e.getMessage()) ;
		}
	}

}
