package com.OTP.Service.OTP.service.Impl;

import com.OTP.Service.OTP.entity.User;
import com.OTP.Service.OTP.service.EmailService;
import com.OTP.Service.OTP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationServiceImpl {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService_Impl emailService_Impl;

    // Temporary storage for OTP
    public static final Map<String, String> emailOtpMapping = new HashMap<>();

    // Method to verify OTP for email verification
    public Map<String, String> verifyOtp(String email, String otp) {
        // Get the OTP
        String storedOtp = emailOtpMapping.get(email);

        Map<String, String> response = new HashMap<>();
        if (storedOtp != null && storedOtp.equals(otp)) {
            // Fetch user by email and mark email as verified
            User user = this.userService.getUseByEmail(email);
            if (user != null) {
                // I Solved Bugs :-
                // Sir, In our project OTP Verification was done But Logic to remove the OTP is missed,
                // that is the moment of the User email is verified remove the OTP is form HashMap
                // Remove OTP from storage once email is verified
                emailOtpMapping.remove(email);

                userService.verifyEmail(user);
                response.put("status", "success");
                response.put("message", "Email Verified");
            } else {
                response.put("status", "error");
                response.put("message", "User is not found");
            }
        } else {
            response.put("status", "error");
            response.put("message", "Invalid OTP");
        }
        return response;
    }

    public Map<String, String> sendOtpForLogin(String email) {
        if (userService.isEmailVerified(email)) {

            String otp = this.emailService_Impl.generateOtp();
            emailOtpMapping.put(email, otp);

            // Send otp user Email
            emailService_Impl.sendOtpEmail(email);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "OTP send successfully");
            return response;
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Email is not verified");
            return response;
        }

    }
}
