package com.OTP.Service.OTP.service.Impl;

import com.OTP.Service.OTP.entity.User;
import com.OTP.Service.OTP.repository.UserRepository;
import com.OTP.Service.OTP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    // Method to register a new user
    @Override
    public User registerUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setId(userId);

        return this.userRepository.save(user);
    }

    // Method to get a user by email
    @Override
    public User getUseByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    // Method to verify user's email
    @Override
    public void verifyEmail(User user) {
        user.setEmailVerified(true);
        this.userRepository.save(user);
    }

    @Override
    public boolean isEmailVerified(String email) {
        User byEmail = this.userRepository.findByEmail(email);
        boolean check = byEmail != null && byEmail.isEmailVerified();
        return check;
    }
}
