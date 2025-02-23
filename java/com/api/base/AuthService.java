package com.api.base;

import java.util.HashMap;

import com.api.model.request.LoginRequest;
import com.api.model.request.SignUpRequest;

import io.restassured.response.Response;

public class AuthService extends BaseService {

	private static final String BASE_PATH = "/api/auth/";
//	private static final String BASE_URI_ = "http://qa ";

	public Response login(LoginRequest paload) {
		return postRequest(paload, BASE_PATH + "login");
	}

	

	public Response signup(SignUpRequest paload) {
		return postRequest(paload, BASE_PATH + "signup");
	}

	public Response forgetPassword(String emailAddress) {
		HashMap<String, String> payload = new HashMap<String, String>();
		payload.put("email", emailAddress);
		return postRequest(payload, BASE_PATH + "forgot-password");
	}
}