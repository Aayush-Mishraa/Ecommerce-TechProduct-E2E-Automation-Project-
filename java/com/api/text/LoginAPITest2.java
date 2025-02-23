package com.api.text;

import org.testng.Assert;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class LoginAPITest2 {

	@Test(description = "werwrewe")
	public void loginTest() {
		RestAssured.baseURI = "http://64.227.160.186:8080";
		RequestSpecification x = RestAssured.given();
		RequestSpecification y = x.header("Content-Type", " application/json");
		RequestSpecification z = y.body("{\r\n" + "  \"username\": \"ayush.am77@gmail.com\",\r\n"
				+ "  \"password\": \" Pass1234@1234\"\r\n" + "}");
		Response response = z.post("/api/auth/login");
		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 200);

	}

}
