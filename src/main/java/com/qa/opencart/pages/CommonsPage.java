package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class CommonsPage {

	// WebDriver instance for interacting with the browser
	private WebDriver driver;
	private ElementUtil eleUtil;

	// Constructor to initialize the WebDriver instance
	public CommonsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By logo = By.className("img-responsive");
	private By footer = By.xpath("//footer//a");

	public boolean isLogoDisplayed() {
		return eleUtil.doIsElementDisplayed(logo);
	}

	public List<String> getFootersList() {
		List<WebElement> footerList = eleUtil.waitForElementsPresence(footer, AppConstants.DEFAULT_TIME_OUT);
		List<String> footers = new ArrayList<String>();

		for (WebElement e : footerList) {
			String text = e.getText();
			footers.add(text);
		}

		return footers;
	}

	public boolean checkFooterLink(String footerLink) {
		return getFootersList().contains(footerLink);
	}

}
