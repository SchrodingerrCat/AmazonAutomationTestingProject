package com.amazon.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.base.BaseClass;
import com.amazon.pages.HomePage;
import com.amazon.pages.LoginPage;
import com.amazon.pages.ProductDetailsPage;
import com.amazon.pages.SearchResultsPage;

public class HomePageTest extends BaseClass{
	
	private LoginPage loginPage ;
	private HomePage homePage ;
	private ProductDetailsPage productDetailsPage ;
	private SearchResultsPage searchResultsPage ;
	
	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(getDriver()) ;
		homePage = new HomePage(getDriver()) ;
		productDetailsPage = new ProductDetailsPage(getDriver()) ;
		searchResultsPage = new SearchResultsPage(getDriver()) ;
	}
	
	@Test
	public void verifyAmazonLogo() {
		loginPage.login("amazonproject44@gmail.com", "randomPass");
		Assert.assertTrue(homePage.verifyAmazonLogo(), "Logo is not visible");
	}
	
	@Test
    public void searchiPhoneAndPrintPriceTest() {
		verifyAmazonLogo() ;
        homePage.searchForProduct("iPhone");
        searchResultsPage.selectFirstProductAndGoToCart();
        
        String price = productDetailsPage.getProductPrice();
        System.out.println("iPhone Price: " + price);
        
        productDetailsPage.removeFromCart();
    }

    @Test
    public void searchGalaxyAndPrintPriceTest() {
    	verifyAmazonLogo() ;
        homePage.searchForProduct("Android Galaxy");
        searchResultsPage.selectFirstProductAndGoToCart();
        
        String price = productDetailsPage.getProductPrice();
        System.out.println("Galaxy Device Price: " + price);
        
        productDetailsPage.removeFromCart();
    }

}
