package com.cap.main.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cap.main.entity.User;
import com.cap.main.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class OTPService {

    @Autowired
    private JavaMailSender mailSender;
    private final EmailService emailService;
    private final UserRepository userRepository;

    public OTPService(EmailService emailService, UserRepository userRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    public String generateOtp() {
        Random random = new Random();
        int otp = random.nextInt(999999 - 100000) + 100000;  // Generate 6-digit OTP
        return String.valueOf(otp);
    }

    public void sendOtpEmail(String toEmail, String otp) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(toEmail);
        helper.setSubject("Your OTP Code");
        helper.setText("Your OTP code is: " + otp);
        mailSender.send(message);
    }
    public void resendOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        String newOtp = generateOtp(); // Use your existing generateOtp() method
        user.setOtpCode(newOtp);
        userRepository.save(user);

        emailService.sendEmail(email, "Resent OTP", "Your new OTP is: " + newOtp);
    }

    
}