package com.amar.fullstack.ecommerce_api.controller;

import com.amar.fullstack.ecommerce_api.dto.auth.request.LoginRequest;
import com.amar.fullstack.ecommerce_api.dto.auth.request.RegisterRequest;
import com.amar.fullstack.ecommerce_api.dto.auth.response.LoginResponse;
import com.amar.fullstack.ecommerce_api.entities.Role;
import com.amar.fullstack.ecommerce_api.entities.User;
import com.amar.fullstack.ecommerce_api.repository.UserRepository;
import com.amar.fullstack.ecommerce_api.security.JwtService;
import com.amar.fullstack.ecommerce_api.services.auth.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequest request){

        authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        Map.of("message","User registered successfully")
                );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){

        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }

}
