package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.amazon.actiondriver.ActionDriver;
import com.amazon.base.BaseClass;

public class ProductDetailsPage {
	
	private ActionDriver actionDriver;
	
    private By priceDisplay = By.xpath("//span[@id='sc-subtotal-amount-buybox']/span");
    private By removeFromCart = By.cssSelector(".a-icon-small-trash") ;
    
    public ProductDetailsPage(WebDriver driver) {
    	this.actionDriver = BaseClass.getActionDriver() ;
    }

    public String getProductPrice() {
        return actionDriver.getAttribute(priceDisplay);
    }
    
    public void removeFromCart() {
    	actionDriver.click(removeFromCart);
    }
}