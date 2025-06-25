package com.smarttasker.user_service.dto;

import com.smarttasker.user_service.model.Role;
import lombok.Getter;

/**
 * Powered by: Shakhzod Akhmedov
 * 6/18/2025
 */
@Getter
public class JwtResponse {
    private String token;
    private String username;
    private String email;
    private Role role;

    public JwtResponse(String token, String username, String email, Role role) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}

