package com.OTP.Service.OTP.repository;

import com.OTP.Service.OTP.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    // Method to find a user by email
    User findByEmail(String email);
}
