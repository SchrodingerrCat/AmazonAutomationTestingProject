package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.amazon.actiondriver.ActionDriver;
import com.amazon.base.BaseClass;

public class SearchResultsPage {
	
	private ActionDriver actionDriver;
	
    private By firstProductLink = By.xpath("(//button[text()='Add to cart'])[1]");
    private By goToCart = By.id("nav-cart-text-container") ;
    
    public SearchResultsPage(WebDriver driver) {
    	this.actionDriver = BaseClass.getActionDriver() ;
    }

    public void selectFirstProductAndGoToCart() {
        actionDriver.click(firstProductLink);
        actionDriver.click(goToCart);
    }
}
