
package com.cap.main.dto;

import lombok.Data;

/**
 * DTO for signup request. Carries user registration details from the client.
 */
@Data
public class SignupRequest {
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
