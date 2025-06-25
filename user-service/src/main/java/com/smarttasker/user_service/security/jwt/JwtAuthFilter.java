package com.smarttasker.user_service.security.jwt;

import com.smarttasker.user_service.entity.User;
import com.smarttasker.user_service.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Powered by: Shakhzod Akhmedov
 * 6/18/2025
 */
@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        System.out.println("[JWT Filter] Path: " + path);

        // Пропускаем публичные эндпоинты
        if (path.equals("/api/auth/register") || path.equals("/api/auth/login")) {
            System.out.println("[JWT Filter] Public path, skipping");
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        System.out.println("[JWT Filter] Authorization header: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("[JWT Filter] No or invalid token");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        System.out.println("[JWT Filter] Token: " + token);

        try {
            String email = jwtUtil.extractUsername(token);
            System.out.println("[JWT Filter] Extracted email: " + email);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Optional<User> userOpt = userRepo.findByEmail(email);
                System.out.println("[JWT Filter] User found: " + userOpt.isPresent());

                if (userOpt.isPresent()) {
                    User user = userOpt.get();
                    System.out.println("[JWT Filter] User role: " + user.getRole().name());

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
                    );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("[JWT Filter] Authentication set for user: " + email);
                }
            }
        } catch (Exception e) {
            System.err.println("[JWT Filter] Error processing token: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
