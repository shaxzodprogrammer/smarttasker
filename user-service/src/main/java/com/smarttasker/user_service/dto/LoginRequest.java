package com.smarttasker.user_service.dto;

import lombok.Data;

/**
 * Powered by: Shakhzod Akhmedov
 * 6/18/2025
 */
@Data
public class LoginRequest {
    private String email;
    private String password;
}