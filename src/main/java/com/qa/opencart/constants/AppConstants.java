package com.qa.opencart.constants;

public class AppConstants {

	public static final int DEFAULT_TIME_OUT = 5;
	public static final int SHORT_TIME_OUT = 10;
	public static final int MEDIUM_TIME_OUT = 15;
	public static final int MAX_TIME_OUT = 20;

	public static final String LOGIN_PAGE_TITILE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";

	public static final String HOME_PAGE_TITILE = "My Account";
	public static final String HOME_PAGE_URL_FRACTION = "route=account/account";
	

    // Path to the configuration properties file
	public static final String CONFIG_PROD_PROP_FILE_PATH = "./src/test/resources/Config/config.properties";
	
	public static final String CONFIG_QA_PROP_FILE_PATH = "./src/test/resources/Config/qa.config.properties";
	public static final String CONFIG_DEV_PROP_FILE_PATH = "./src/test/resources/Config/dev.config.properties";
	public static final String CONFIG_STAGE_PROP_FILE_PATH = "./src/test/resources/Config/stage.config.properties";
	public static final String CONFIG_UAT_PROP_FILE_PATH = "./src/test/resources/Config/uat.config.properties";
	
	//*********sheet names*******//
	public static final String PRODUCT_SHEET_NAME = "product";

}
