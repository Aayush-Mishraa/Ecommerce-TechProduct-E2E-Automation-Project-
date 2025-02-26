package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class HomePageTest extends BaseTest {

	@BeforeClass
	public void homePageSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void homePageTitleTest() {
		Assert.assertEquals(homePage.getHomePageTitle(), "My Account", "==title is not matched==");

	}

	@Test
	public void homePageURLTest() {
		Assert.assertTrue(homePage.getHomePageURL().contains("route=account/account"), "==URL is not matched==");

	}

	@Test
	public void logoutLinkExistTest() {
		Assert.assertTrue(homePage.isLogoutLinkExist(), "==logout link is not prenets==");
	}

	public void headersTest() {

		List<String> actualHeaders = homePage.getHeaderList();
		System.out.println("Home page headers:==" + actualHeaders);
	}

	@DataProvider 
	public Object[][] getSearchData() {
		return new Object[][] {
			{"macbook", 3},
			{"imac", 1},
			{"samsung",2},
			{"canon",1},
			{"airtel",0}
		};
	}

	@Test(dataProvider = "getSearchData")
	public void searchTest(String searchkey, int resultcount)  {
		searchResultsPage = homePage.doSearch(searchkey);
		Assert.assertEquals(searchResultsPage.getProductResultsCount(), resultcount);
	}

}
