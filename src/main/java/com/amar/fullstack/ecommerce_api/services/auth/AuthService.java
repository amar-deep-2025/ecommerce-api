package com.amar.fullstack.ecommerce_api.services.auth;

import com.amar.fullstack.ecommerce_api.dto.auth.request.LoginRequest;
import com.amar.fullstack.ecommerce_api.dto.auth.request.RegisterRequest;
import com.amar.fullstack.ecommerce_api.dto.auth.response.LoginResponse;
import com.amar.fullstack.ecommerce_api.entities.Role;
import com.amar.fullstack.ecommerce_api.entities.User;
import com.amar.fullstack.ecommerce_api.repository.UserRepository;
import com.amar.fullstack.ecommerce_api.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder=passwordEncoder;
    }

    public void register(RegisterRequest request) {
        log.info("REGISTER API called | email={}",request.getEmail());
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            log.warn("REGISTRATION FAILED | email already exists{} ",request.getEmail());
            throw new RuntimeException("Email already exists");

        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
    }
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.warn("LOGIN FAILED | invalid password | email={}", request.getEmail());
            throw new RuntimeException("Password is incorrect");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole().name(), "ACCESS");
        LoginResponse response=new LoginResponse();
        response.setAccessToken(token);
        response.setTokenType("ACCESS");
        response.setMessage("Login successful");
        return  response;

    }
}
