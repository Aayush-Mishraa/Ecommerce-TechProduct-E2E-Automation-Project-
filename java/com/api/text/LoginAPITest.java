package com.api.text;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.model.request.LoginRequest;
import com.api.model.response.LoginResponse;

import io.restassured.response.Response;

@Listeners(com.api.listeners.TestListener.class)

public class LoginAPITest {

	@Test(description = "Verify if login API is working......")
	public void loginTest() {
		LoginRequest loginRequest = new LoginRequest("uday1234", "uday1234");
		AuthService authService = new AuthService();
		Response response = authService.login(loginRequest);
		System.out.println(response.asPrettyString());
		LoginResponse loginResponse = response.as(LoginResponse.class);
		System.out.println(response.asPrettyString());
		System.out.println(loginResponse.getToken());
		System.out.println(loginResponse.getEmail());
		System.out.println(loginResponse.getId());

		Assert.assertTrue(loginResponse.getToken() != null);
		Assert.assertEquals(loginResponse.getEmail(), "rish@gmail.com");
		Assert.assertEquals(loginResponse.getId(), 3);
	}

}
