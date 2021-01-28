package com.qa.global.util;

public class CommonUtils {

	public String getAuthCode(String authCodeUrl) {
		return authCodeUrl.split("&")[0].split("=")[1];
	}
}