package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class HomePage {

	// WebDriver instance for interacting with the browser
	private WebDriver driver;
	private ElementUtil eleUtil;

	// Constructor to initialize the WebDriver instance
	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By logoutLink = By.linkText("Logout");
	private By headers = By.cssSelector("div#content > h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	// 2 public page action - method (Features)
	
	public String getHomePageTitle() {
		String title = driver.getTitle();
		System.out.println("Login Page Title==>" + title);
		ChainTestListener.log("Login page title==>"+title);
		return title;
	}

	public String getHomePageURL() {
		String url = driver.getCurrentUrl();
		System.out.println("Login Page Title==>" + url);
		return url;
	}

	public boolean isLogoutLinkExist() {
		return driver.findElement(logoutLink).isDisplayed();
	}

	public void logout() {
		if (isLogoutLinkExist()) {
			driver.findElement(logoutLink).click();
		}
	}

	public List<String> getHeaderList() {

		List<WebElement> headerList = driver.findElements(headers);
		List<String> headerValueList = new ArrayList<String>();
		for (WebElement e : headerList) {
			String text = e.getText();
			headerValueList.add(text);
		}
		return headerValueList;
	}

	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("Search key " + searchKey);

		WebElement searchEle = eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_TIME_OUT);
		eleUtil.doSendKeys(searchEle, searchKey);

		driver.findElement(searchIcon).click();
		return new SearchResultsPage(driver);
	}

}
