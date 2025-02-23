package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// Constructor to initialize the WebDriver instance
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	
	private By productResults = By.cssSelector("div.product-thumb");
	
	
	public int getProductResultsCount() {
		int resultCount =	eleUtil.waitForElementsPresence(productResults, AppConstants.SHORT_TIME_OUT).size();	
//		int resultCount = driver.findElements(productResults).size();
		System.out.println("Product Result Count ===> "+resultCount);
		return resultCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		
		System.out.println("Product name: "+ productName);
		eleUtil.doClick(By.linkText(productName));
//		driver.findElement(By.linkText(productName)).click();
		return new ProductInfoPage(driver);
	}

}
