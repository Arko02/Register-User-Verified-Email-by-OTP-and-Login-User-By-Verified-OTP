package com.OTP.Service.OTP.service;

import com.OTP.Service.OTP.entity.User;

public interface UserService {
    // Method to register a new user
    User registerUser(User user);

    // Method to get a user by email
    User getUseByEmail(String email);

    // Method to verify user's email
    void verifyEmail(User user);

    boolean isEmailVerified(String email);
}
