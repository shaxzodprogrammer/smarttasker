package com.smarttasker.user_service.repository;

import com.smarttasker.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Powered by: Shakhzod Akhmedov
 * 6/17/2025
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
