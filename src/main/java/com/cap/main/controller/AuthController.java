package com.cap.main.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.main.dto.AuthRequest;
import com.cap.main.entity.User;
import com.cap.main.repository.UserRepository;
import com.cap.main.security.JWTService;
import com.cap.main.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
//    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    // Constructor Injection
    public AuthController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder,JWTService jwtService) {
        this.userService = userService;
        this.userRepository = userRepository;
//        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.jwtService =jwtService;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody AuthRequest request) {
        userService.signup(request.getEmail(), request.getPassword());
        return "OTP has been sent to your registered email.";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp) {
        return userService.verifyOtp(email, otp)
                ? "OTP Verified Successfully!"
                : "Invalid OTP. Please try again.";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!user.isOtpVerified()) {
            throw new IllegalStateException("Please verify OTP before logging in.");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String token = jwtService.generateToken(user.getEmail());
        return token;
    }


}