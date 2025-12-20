package com.amar.fullstack.ecommerce_api.controller;

import com.amar.fullstack.ecommerce_api.dto.RegisterRequest;
import com.amar.fullstack.ecommerce_api.entities.Role;
import com.amar.fullstack.ecommerce_api.entities.User;
import com.amar.fullstack.ecommerce_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest dto){

        User user=new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return "User registered successfully";
    }


}
