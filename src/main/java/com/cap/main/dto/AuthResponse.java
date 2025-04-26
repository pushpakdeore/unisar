package com.cap.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A simple POJO (Plain Old Java Object) that represents the response returned after a successful authentication.
 * This typically includes the JWT token that the client will use for subsequent authorized requests.
 */
@Data
@NoArgsConstructor
public class AuthResponse {

    /**
     * The JWT token that is returned to the client after successful login.
     */
    private String token;

	public AuthResponse(String token) {
		super();
		this.token = token;
	}
    
    
}