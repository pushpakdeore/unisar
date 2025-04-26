package com.cap.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A simple POJO (Plain Old Java Object) that represents the request sent by the
 * client during login or signup. This typically includes the email and password
 * that the client sends.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

	/**
	 * The email of the user for login/signup.
	 */
	private String email;

	/**
	 * The password of the user for login/signup.
	 */
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}