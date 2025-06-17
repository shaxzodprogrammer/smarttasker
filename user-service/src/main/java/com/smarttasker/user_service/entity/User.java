package com.smarttasker.user_service.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Powered by: Shakhzod Akhmedov
 * 6/17/2025
 */
@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String role; // USER / ADMIN

}
