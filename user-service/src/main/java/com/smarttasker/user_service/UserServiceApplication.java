package com.smarttasker.user_service;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	@PostConstruct
	public void checkEnv() {
		System.out.println("🔍 SPRING_DATASOURCE_URL = " + System.getenv("SPRING_DATASOURCE_URL"));
	}
}
