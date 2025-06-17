package com.smarttasker.user_service.controller;

import com.smarttasker.user_service.dto.UserRegisterRequest;
import com.smarttasker.user_service.entity.User;
import com.smarttasker.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
        User user = userService.register(request);
        return ResponseEntity.ok(user);
    }
}
