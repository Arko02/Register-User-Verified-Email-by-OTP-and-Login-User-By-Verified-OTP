package com.OTP.Service.OTP.controller;

import com.OTP.Service.OTP.service.Impl.EmailVerificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private EmailVerificationServiceImpl emailVerificationServiceImpl;

    @PostMapping("/send-opt") // http://localhost:8080/login/send-opt?email=
    public Map<String, String> sendOtpForLogin(@RequestParam String email) {
        Map<String, String> stringStringMap = this.emailVerificationServiceImpl.sendOtpForLogin(email);
        return stringStringMap;
    }

    @PostMapping("/verify-otp") // http://localhost:8080/login/verify-otp?email=&otp=
    public Map<String, String> verifyOtpForLogin(@RequestParam String email, @RequestParam String otp) {
        return emailVerificationServiceImpl.verifyOtp(email, otp);
    }
}
