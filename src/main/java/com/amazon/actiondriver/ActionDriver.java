package com.amazon.actiondriver;

import java.time.Duration;
import com.amazon.base.BaseClass;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.base.BaseClass;

public class ActionDriver {
	
	private WebDriver driver ;
	private WebDriverWait wait;
	public static final Logger logger = BaseClass.logger ;
	
	public ActionDriver(WebDriver driver) {
		this.driver = driver ;
		int explicitWait = Integer.parseInt(BaseClass.getProp().getProperty("explicitWait")) ;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
		logger.info("WebDriver instance is created.");
	}
	
	//Method to click an element
	public void click(By by) {
		String elementDescription = getElementDescription(by) ;
		try {
			waitForElementToBeClickable(by) ;
			driver.findElement(by).click();
			logger.info("clicked an element--> " + elementDescription) ;
		} catch (Exception e) {
			System.out.println("Unable to click the element: "+e.getMessage());
			logger.error("unable to click element");
		}
	}
	
	//Method to get an element
		public WebElement findElement(By by) {
			try {
				waitForElementToBeClickable(by) ;
				WebElement element = driver.findElement(by);
				logger.info("found element") ;
				return element ;
			} catch (Exception e) {
				System.out.println("Unable to find the element: "+e.getMessage());
				logger.error("unable to find element");
			}
			return null;
		}
		
		//Method to getAttribute
		public String getAttribute(By by) {
			try {
				waitForElementToBeVisible(by);
				return driver.findElement(by).getAttribute("textContent");
			} catch (Exception e) {
				logger.error("Unable to get the text: " + e.getMessage());
				return "" ;
			}
		}
		
	
	//Method to enter text into an input field
	public void enterText(By by, String value) {
		try {
			waitForElementToBeVisible(by);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(value);
			logger.info("Entered text on " + getElementDescription(by) + "-->" + value);
		} catch (Exception e) {
			logger.error("Unable to enter the value: "+ e.getMessage());
		}
	}
	
	//Method to get text from an input field
	public String getText(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			logger.error("Unable to get the text: " + e.getMessage());
			return "" ;
		}
	}
	
	//Method to compare Two text
	public boolean compareText(By by, String expectedText) {
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText() ;
			if(expectedText.equals(actualText)) {
				logger.info("Text are Matching: " + actualText + " equals " + expectedText);
				return true ;
			}
			else {
				logger.error("Text are not Matching: " + actualText + " not equals " + expectedText);
				return false ;
			}
		} catch (Exception e) {
			logger.error("Unable to compare texts" + e.getMessage());
		}
		return false ;
	}
	
	//Method to check if an element is displayed
	public boolean isDisplayed(By by) {
		try {
			waitForElementToBeVisible(by);
			logger.info("Element is displayed: " + getElementDescription(by)) ;
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			logger.error("Element is not displayed: "+ e.getMessage());
			return false ;
		}
	}
	
	//Wait for the page to load
	public void waitForPageLoad(int timeOutInSec) {
		try {
			wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> ((JavascriptExecutor) WebDriver)
					.executeScript("return document.readyState").equals("complete")) ;
			logger.info("Page loaded successfully");
		} catch (Exception e) {
			logger.error("Page did not load within " + timeOutInSec + " seconds. Exception: " + e.getMessage());
		}
	}
	
	//Scroll to an element
	public void scrollToElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver ;
			WebElement element = driver.findElement(by) ;
			js.executeScript("arguments[0],scrollIntoView(true);", element) ;
		} catch (Exception e) {
			logger.error("Unable to locate element: " + e.getMessage());
		}
	}
	
	//Wait for element to be clickable
	private void waitForElementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by)) ;
		} catch (Exception e) {
			logger.error("element is not clickable: "+e.getMessage());
		}
	}
	
	//Wait for element to be visible
	public void waitForElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by)) ;
		} catch (Exception e) {
			logger.error("Element is not visible: "+e.getMessage()) ;
		}
	}
	
	//Method to get the description of an element using By locator 
	public String getElementDescription(By locator) {
		// Check for null driver or locator to avoid NULLPointer Exception
		if(driver == null) {
			return "driver is null" ;
		}
		if(locator == null) {
			return "Locator is null" ;
		}
		
		try {
			//find the element using the locator
			WebElement element = driver.findElement(locator) ;
			
			//Get Element Attributes
			String name = element.getDomAttribute("name") ;
			String id = element.getDomAttribute("id") ;
			String text = element.getText() ;
			String className = element.getDomAttribute("class") ;
			String placeHolder = element.getDomAttribute("placeholder") ;
			
			//Return the description based on element attributes
			if(isNotEmpty(name)) {
				return "Element with name:" + name ;
			} else if(isNotEmpty(id)) {
				return "Element with id:" + id ;
			} else if(isNotEmpty(text)) {
				return "Element with text:" + truncate(text,50) ;
			} else if(isNotEmpty(className)) {
				return "Element with className:" + className ;
			} else if(isNotEmpty(placeHolder)) {
				return "Element with placeHolder:" + placeHolder ;
			}
		} catch (Exception e) {
			logger.error("Unable to describe the element: " + e.getMessage()) ;
		}
		return "Unable to describe the element" ; 
		
		
	}
	
	//Utility method to check a String is not NULL or empty
	private boolean isNotEmpty(String value) {
		return value != null && !value.isEmpty();
	}
	
	// Utility Method to truncate long String
	private String truncate(String value, int maxLength) {
		if(value==null || value.length() <= maxLength) {
			return value ;
		}
		return value.substring(0,maxLength) + "..." ;
	}
	

}
