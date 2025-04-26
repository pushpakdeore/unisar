package com.cap.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.main.service.OTPService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private OTPService otpService;

    @PostMapping("/signup")
    public String signup(@RequestParam String email) throws MessagingException {
        // Generate OTP
        String otp = otpService.generateOtp();

        // Send OTP via email
        otpService.sendOtpEmail(email, otp);

        // You can store the OTP temporarily (e.g., in the database or cache) for later verification
        return "OTP sent to your email";
    }
}