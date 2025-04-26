package com.cap.main.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cap.main.entity.User;
import com.cap.main.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final OTPService otpService;
    

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService,
			OTPService otpService) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
		this.otpService = otpService;
	}

	/**
     * Signs up a new user by storing their email and encoded password in the
     * database. An OTP is generated and sent to the user's email.
     *
     * @param email    the email of the user
     * @param password the password for the user
     */
    public void signup(String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setOtpVerified(false);

        // Generate OTP and send via email
        String otp = otpService.generateOtp();
        emailService.sendEmail(email, "Your OTP Code", "Your OTP is: " + otp);

        // Save OTP (for demo only, should be in cache or DB with TTL in real app)
        user.setOtpCode(otp);

        userRepository.save(user);
    }

    /**
     * Verifies the OTP entered by the user.
     *
     * @param email the email of the user
     * @param otp   the OTP entered by the user
     * @return true if the OTP is correct and the user is verified
     */
    public boolean verifyOtp(String email, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        if (!user.isOtpVerified()) {
            user.setOtpVerified(true);
            userRepository.save(user);
            return true;
        }

        return false;
    }
    
}