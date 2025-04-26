package com.cap.main.exception;

/**
 * Custom exception thrown when user credentials are invalid during login.
 * Helps return meaningful messages instead of generic Spring Security errors.
 */
public class InvalidCredentialsException extends RuntimeException {

    /**
     * Constructs a new InvalidCredentialsException with a default message.
     */
    public InvalidCredentialsException() {
        super("Invalid email or password");
    }

    /**
     * Constructs a new InvalidCredentialsException with a custom message.
     * @param message The custom message to be returned.
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
