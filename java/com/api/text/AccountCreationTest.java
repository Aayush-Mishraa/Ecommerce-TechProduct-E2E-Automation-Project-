package com.api.text;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.model.request.SignUpRequest;

import io.restassured.response.Response;

public class AccountCreationTest {

	@Test(description = "Verify is working...")

	public void creatAccountTest() {

		SignUpRequest signUpRequest = new SignUpRequest.Builder().userName("Ayush1234")
				.email("aaaayushmishra1026@gmaail.com").passWord("Aayush1234").firstName("Aayush").lastName("Mishra")
				.mobileNumber("12454567890").build();

		AuthService authService = new AuthService();
		Response response = authService.signup(signUpRequest);
		Assert.assertEquals(response.asPrettyString(), "Error: Username is already taken!");
		System.out.println(response.asPrettyString());

	}

}
