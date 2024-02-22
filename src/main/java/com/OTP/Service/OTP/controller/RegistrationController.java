package com.OTP.Service.OTP.controller;

import com.OTP.Service.OTP.entity.User;
import com.OTP.Service.OTP.service.EmailService;
import com.OTP.Service.OTP.service.Impl.EmailVerificationServiceImpl;
import com.OTP.Service.OTP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailVerificationServiceImpl emailVerificationServiceImpl;

    // Endpoint to register a new user and send OTP for email verification
    @PostMapping("/register") // http://localhost:8080/api/register
    public Map<String, String> registerUser(@RequestBody User user) {
        // Register User without Email Verification
        this.userService.registerUser(user);

        // Send OTP for Email verification
        this.emailService.sendOtpEmail(user.getEmail());

        // Prepare response
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User register successfully. Check your email for verification.");
        return response;
    }

    // Endpoint to verify OTP for email verification
    @PostMapping("/verify-otp") // http://localhost:8080/api/verify-otp?email=&otp=
    public Map<String, String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        return this.emailVerificationServiceImpl.verifyOtp(email, otp);
    }
}
