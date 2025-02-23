package com.api.text;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.model.request.SignUpRequest;

import io.restassured.response.Response;

public class ForgotPasswordTest {

	@Test(description = "Verify is working...")

	public void forgotPasswordTest() {

		AuthService authService = new AuthService();
		Response response = authService.forgetPassword("aysh.am77@gmail.com");
		System.out.println(response.asPrettyString());
	}

}
