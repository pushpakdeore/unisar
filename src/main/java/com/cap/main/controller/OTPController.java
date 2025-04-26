package com.cap.main.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.main.service.OTPService;

@RestController
@RequestMapping("/api/otp")
//@RequiredArgsConstructor
public class OTPController {

    private final OTPService otpService;
    

    public OTPController(OTPService otpService) {
		super();
		this.otpService = otpService;
	}


	@PostMapping("/resend")
    public String resendOtp(@RequestParam String email) {
        otpService.resendOtp(email);
        return "OTP has been resent to your email.";
    }
}