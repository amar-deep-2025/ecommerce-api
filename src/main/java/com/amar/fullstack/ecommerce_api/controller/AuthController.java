package com.amar.fullstack.ecommerce_api.controller;

import com.amar.fullstack.ecommerce_api.dto.LoginRequest;
import com.amar.fullstack.ecommerce_api.dto.RegisterRequest;
import com.amar.fullstack.ecommerce_api.entities.Role;
import com.amar.fullstack.ecommerce_api.entities.User;
import com.amar.fullstack.ecommerce_api.repository.UserRepository;
import com.amar.fullstack.ecommerce_api.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger log =
            LoggerFactory.getLogger(AuthController.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public AuthController(UserRepository userRepository,
            PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest dto) {
        log.info("REGISTER API called | email={}",dto.getEmail());
        if (userRepository.findByEmail(dto.getEmail()).isPresent()){
            log.warn("REGISTRATION FAILED | email already exists{} ",dto.getEmail());
            return "Email already registered";
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);

        log.info("REGISTRATION SUCCESS | UserId={} | email={}",user.getId(),user.getEmail());
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest dto) {

        log.info("LOGIN ENTRY | email={}",dto.getEmail());

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user == null) {
            log.warn("LOGIN FAILED | user not found | email={}",dto.getEmail());
            return "User not found";

        }

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            log.warn("LOGIN FAILED | invalid password | email={}", dto.getEmail());
            return "Incorrect Password";
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        log.info("LOGIN SUCCESS | UserId={} | email={}",user.getId(),user.getEmail());
        return token;
    }

}
