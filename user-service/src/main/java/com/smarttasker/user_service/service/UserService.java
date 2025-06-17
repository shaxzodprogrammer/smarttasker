package com.smarttasker.user_service.service;

import com.smarttasker.user_service.dto.UserRegisterRequest;
import com.smarttasker.user_service.entity.User;
import com.smarttasker.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Powered by: Shakhzod Akhmedov
 * 6/17/2025
 */
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public User register(UserRegisterRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        return userRepo.save(user);
    }
}
