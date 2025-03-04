package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: design login page for open cart")
@Story("Us 101: design the various feature of open cart login page")
@Feature("Feature 50: Logine  Page Feature")
public class LoginPageTest extends BaseTest {


	@Description("Checking Login Page titile....")
	@Severity(SeverityLevel.MINOR)
	

	@Test
	public void loginPageTitleTest() {
		ChainTestListener.log("Verify loging page Title ");
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITILE, AppError.TITILE_NOT_FOUND_ERROR);
	}
	
	
	
	@Description("Checking Login Page URL....")
	@Severity(SeverityLevel.MINOR)

	@Test(enabled = true)
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND_ERROR);
	}
	@Description("Checking Forgot Password LInk....")
	@Severity(SeverityLevel.CRITICAL)

	@Test(enabled = true)
	public void forgetPwdLinkExitsTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExits(), AppError.ELEMENT_NOT_FOUND_ERROR);
	}

	@Description("Checking user is able to login with the right cradential or not....")
	@Severity(SeverityLevel.BLOCKER)	
	@Test(priority = Integer.MAX_VALUE, enabled = false)
	public void loginTest() {
		// when ever we calling any page we have to return next landing page object

		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homePage.getHomePageTitle(), AppConstants.HOME_PAGE_TITILE,
				AppError.TITILE_NOT_FOUND_ERROR);
	}
	@Description("Checking Logo of the Page.......")
	@Severity(SeverityLevel.BLOCKER)	
	@Test(enabled = true)
	public void logoTest() {
		Assert.assertTrue(commonsPage.isLogoDisplayed(), AppError.LOG_NOT_FOUND_ERROR);
	}

	@DataProvider
	public Object[][] getFooterData() {
		return new Object[][] { { "About Us" }, { "Delivery Information" }, { "Order History" }, { "My Account" },
				{ "Wish List" }, { "Newsletter" }

		};
	}
	@Description("Checking page footer")
	@Severity(SeverityLevel.NORMAL)	
	@Test(dataProvider = "getFooterData",enabled = true)
	public void footersTest(String footerName) {
		Assert.assertTrue(commonsPage.checkFooterLink(footerName));
	}
}
