package com.smarttasker.user_service.dto;

import lombok.Data;

/**
 * Powered by: Shakhzod Akhmedov
 * 6/17/2025
 */
@Data
public class UserRegisterRequest {
    private String username;
    private String email;
    private String password;
}
