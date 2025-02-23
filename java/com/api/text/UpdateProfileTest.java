package com.api.text;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.model.request.LoginRequest;
import com.api.model.request.ProfileRequest;
import com.api.model.request.ProfileRequest.Builder;
import com.api.model.response.LoginResponse;
import com.api.model.response.UserProfileResponse;

import io.restassured.response.Response;

public class UpdateProfileTest {

	@Test
	public void updateProfileTest() {
		AuthService authService = new AuthService();

		Response response = authService.login(new LoginRequest("uday1234", "uday1234"));
		LoginResponse loginResponse = response.as(LoginResponse.class);

		System.out.println(loginResponse.getToken());

		System.out.println("--------------------------------------------------------");
		UserProfileManagementService userProfileManagementService = new UserProfileManagementService();

		response = userProfileManagementService.getProfile(loginResponse.getToken());
		System.out.println(response.asPrettyString());

		UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
		Assert.assertEquals(userProfileResponse.getUsername(), "uday1234");;

		System.out.println("--------------------------------------------------------");

		ProfileRequest profileRequest = new ProfileRequest.Builder()
			  	.firstName("Ayush")
				.lastName("mishra")
				.email("ayuush123@gmail.com")
				.mobileNumber("1122311231")
				.build();
		
		response = userProfileManagementService.updateProfile(loginResponse.getToken(), profileRequest);
		
		System.out.println(response.asPrettyString());
	}
}
