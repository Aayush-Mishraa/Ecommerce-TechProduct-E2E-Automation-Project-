package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productMap;

	// Constructor to initialize the WebDriver and ElementUtil instances
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// By locators for various elements on the product information page
	private By productHeader = By.tagName("h1"); // Locator for the product header
	private By productImages = By.cssSelector("ul.thumbnails img"); // Locator for product images
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li"); // Locator for
																										// product
																										// metadata
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li"); // Locator for
																										// product price
																										// da

	// Method to get the product header text
	public String getProductHeader() {
		// Fetches the text of the product header element
		String header = eleUtil.doElementGetText(productHeader);
		System.out.println("Product header ====> " + header); // Prints the header to the console for debugging
		return header; // Returns the header text
	}

	// Method to get the count of product images
	public int getProductImagesCount() {
		// Waits for the presence of product images and gets the count of the image
		// elements
		int imagesCount = eleUtil.waitForElementsPresence(productImages, AppConstants.SHORT_TIME_OUT).size();
		System.out.println(getProductHeader() + ": Images Count " + imagesCount); // Prints the product header and image
																					// count for debugging
		return imagesCount; // Returns the count of images
	}

	// Method to get all product information as a map
	public Map<String, String> getProductInfo() {
		// Using HashMap (default): no guaranteed order of elements
		// productMap = new HashMap<String, String>();

		// Using LinkedHashMap: maintains insertion order of elements
		// productMap = new LinkedHashMap<String, String>();

		// Using TreeMap: maintains elements in natural alphabetical order of keys
		// Initializing productMap as a TreeMap to maintain sorted order of keys
		productMap = new TreeMap<String, String>();
		// Adding product header and image count to the map
		productMap.put("Header", getProductHeader());
		productMap.put("Images Count", getProductImagesCount() + "");
		// Fetching and adding product metadata and price data to the map
		getProductMetaData();
		getProductPriceData();
		System.out.println("---------------------");

		return productMap; // Returns the map containing all product information
	}

	// Method to get product metadata (e.g., brand, availability)
	private void getProductMetaData() {
		// Waits for the presence of metadata elements
		List<WebElement> metaData = eleUtil.waitForElementsPresence(productMetaData, AppConstants.DEFAULT_TIME_OUT);
		// Iterates through each metadata element
		for (WebElement e : metaData) {
			// Splits the metadata text into key and value parts
			String metaText = e.getText();
			String[] meta = metaText.split(":");
			String metaKey = meta[0].trim(); // Extracts and trims the metadata key
			String metaValue = meta[1].trim(); // Extracts and trims the metadata value
			// Adds the metadata key-value pair to the product map
			productMap.put(metaKey, metaValue);
		}
	}

	// Method to get product price data (e.g., price, tax)
//	private void getProductPriceData() {
//		// Waits for the presence of price data elements
//		List<WebElement> priceList = eleUtil.waitForElementsPresence(productPriceData, AppConstants.DEFAULT_TIME_OUT);
//		// Extracts the product price and tax text
//		String productPrice = priceList.get(0).getText().trim();
//		String productTax = priceList.get(1).getText().split(":")[1].trim();
//		// Adds the product price and tax to the product map
//		productMap.put("Price", productPrice);
//		productMap.put("Tax", productTax);
//		
//		
//	}
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.waitForElementsPresence(productPriceData, AppConstants.DEFAULT_TIME_OUT);
		String Productprice = priceList.get(0).getText().trim();
		String productExTax = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("price", Productprice);
		productMap.put("extax", productExTax);
	}
}
