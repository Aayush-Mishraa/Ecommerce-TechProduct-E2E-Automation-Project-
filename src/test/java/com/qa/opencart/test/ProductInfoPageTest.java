package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {

	// Setup method to perform login before the test class is executed
	@BeforeClass
	public void productInfoSetup() {
		homePage = loginePage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	// Data provider for product data
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "macbook", "MacBook Pro" }, { "macbook", "MacBook Air" }, { "imac", "iMac" },
				{ "samsung", "Samsung SyncMaster 941BW" }, { "samsung", "Samsung Galaxy Tab 10.1" } };
	}

	// Test method to verify the product header matches the expected product name
	@Test(dataProvider = "getProductData")
	public void productSearchHeaderTest(String searchKey, String productName) {
		searchResultsPage = homePage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		String actualProductHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actualProductHeader, productName);
	}

	// Data provider for product image data
	@DataProvider
	public Object[][] getProductImageData() {
		return new Object[][] { { "macbook", "MacBook Pro", 4 }, { "macbook", "MacBook Air", 3 }, { "imac", "iMac", 3 },
				{ "samsung", "Samsung SyncMaster 941BW", 1 }, { "samsung", "Samsung Galaxy Tab 10.1", 7 } };
	}

//	@DataProvider
//	public Object[][] getProductImageSheetData() {
//		Object productData[][] = ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
//		return productData;
//	}
//
//	// Test method to verify the count of product images matches the expected count
//
////    @Test(dataProvider = "getProductImageData")
//
//	@Test(dataProvider = "getProductImageSheetData")
//	public void productImagesCountTest(String searchKey, String productName, String expectedImagesCount) {
//		searchResultsPage = homePage.doSearch(searchKey);
//		productInfoPage = searchResultsPage.selectProduct(productName);
//		int actutalProductImagesCount = productInfoPage.getProductImagesCount();
//		Assert.assertEquals(actutalProductImagesCount, Integer.parseInt(expectedImagesCount));
//	}

	// Test method to verify various product information and perform multiple
	// assertions using SoftAssert
	@Test
	public void productInfoTest() {
		searchResultsPage = homePage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productInfoMap = productInfoPage.getProductInfo();
		productInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));

		// Using SoftAssert for multiple assertions
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productInfoMap.get("Header"), "MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfoMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("extax"), "$2,000.00");

		// Check all assertions at the end and provide the output of failed assertions
		softAssert.assertAll();
	}
}
