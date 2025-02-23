package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	// WebDriver instance for interacting with the browser
	private WebDriver driver;
	private ElementUtil eleUtil;

	// Constructor to initialize the WebDriver instance
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// Locators for web elements on the login page
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value ='Login']");
	private By forgotpwdLink = By.linkText("Forgotten Password");

	// Public methods to perform actions on the login page (features)

	/**
	 * Get the title of the login page
	 * 
	 * @return title of the login page
	 */
	
	@Step("getHomePageTitle")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITILE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Login Page Title==>" + title);
		return title;
	}

	/**
	 * Get the current URL of the login page
	 * 
	 * @return URL of the login page
	 */
	@Step("getHomePageTitle")
	public String getLoginPageURL() {
		String url	= eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Login Page URL==>" + url);
		return url;
	}

	/**
	 * Check if the "Forgotten Password" link exists on the login page
	 * 
	 * @return true if the link is displayed, false otherwise
	 */
	@Step("is Forgot Pwd Link is Exits")
	public boolean isForgotPwdLinkExits() {
		return eleUtil.doIsElementDisplayed(forgotpwdLink);
	}

	/**
	 * Perform login action with provided username and password
	 * 
	 * @param username the username to log in with
	 * @param pwd      the password to log in with
	 * @return a new instance of the HomePage class
	 */
	@Step("Login with username: {0} and passwored:{1}")
	public HomePage doLogin(String username, String pwd) {
		System.out.println("App credentials are: ==>" + username + ":" + pwd);
		
		eleUtil.waitForElementVisible(emailId,  AppConstants.SHORT_TIME_OUT).sendKeys(username);
	    eleUtil.doSendKeys(password, pwd);
	    eleUtil.doClick(loginBtn);
		// When we click the login button and land on a different page, always return
		// the landing page class object
		return new HomePage(driver);
	}
}
