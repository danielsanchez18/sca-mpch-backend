package com.mpch.controlDeAsistencias_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpch.controlDeAsistencias_backend.model.Login;
import com.mpch.controlDeAsistencias_backend.model.User;
import com.mpch.controlDeAsistencias_backend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login loginRequest) {
        logger.info("Login request received for DNI: {}", loginRequest.getDni());
        try {
            String token = authService.login(loginRequest.getDni(), loginRequest.getPassword());
            logger.info("Login successful for DNI: {}", loginRequest.getDni());
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } catch (RuntimeException ex) {
            logger.error("Login failed for DNI: {} with error: {}", loginRequest.getDni(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", ex.getMessage()));
        }
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) {
        logger.info("Request for current user with token: {}", token);
        try {
            User currentUser = authService.getCurrentUser(token.replace("Bearer ", ""));
            logger.info("Successfully retrieved current user: {}", currentUser.getName());
            return ResponseEntity.ok(currentUser);
        } catch (RuntimeException ex) {
            logger.error("Failed to retrieve current user with error: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Collections.singletonMap("error", ex.getMessage()));
        }
    }
}
