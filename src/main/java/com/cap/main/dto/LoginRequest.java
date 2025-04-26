package com.cap.main.dto;

import lombok.Data;

/**
 * DTO for login request. Carries login credentials from the client to the
 * server.
 */
@Data
public class LoginRequest {
	private String email;
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