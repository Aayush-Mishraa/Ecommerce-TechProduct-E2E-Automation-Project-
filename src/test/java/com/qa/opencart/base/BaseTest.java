package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.chaintest.service.ChainPluginService;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;


public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	
	protected Properties prop;
	
	protected CommonsPage commonsPage;
	protected LoginPage loginePage;
	protected HomePage homePage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browseName) {
		
		
		df= new DriverFactory();
		
		//we inicilize the property file 
		prop = df.initProp();
		if(browseName != null) {
			prop.setProperty("browser", browseName);
		}
		
		
		driver = df.initDriver(prop);
		
		loginePage = new LoginPage(driver);
		commonsPage = new CommonsPage(driver);
		ChainPluginService.getInstance().addSystemInfo("Build#","1.0");
		ChainPluginService.getInstance().addSystemInfo("headless#",prop.getProperty("headless"));
		ChainPluginService.getInstance().addSystemInfo("incognito#",prop.getProperty("incognito"));
		ChainPluginService.getInstance().addSystemInfo("Owner#", "Naveen Automation Labs");
	}
	
	
	@AfterMethod
	public void attachScreenshot(ITestResult result) {
		
		if(!result.isSuccess()) {
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
		
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
