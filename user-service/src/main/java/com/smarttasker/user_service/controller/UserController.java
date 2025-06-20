package com.smarttasker.user_service.controller;

import com.smarttasker.user_service.dto.JwtResponse;
import com.smarttasker.user_service.dto.LoginRequest;
import com.smarttasker.user_service.dto.UserRegisterRequest;
import com.smarttasker.user_service.entity.User;
import com.smarttasker.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * Powered by: Shakhzod Akhmedov
 * 6/17/2025
 */
@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        JwtResponse jwt = userService.login(request);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
            System.out.println( "sukaaaa" + authentication.getPrincipal().toString());
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(Map.of(
                "username", user.getUsername(),
                "email", user.getEmail(),
                "role", user.getRole()
        ));
    }
}
