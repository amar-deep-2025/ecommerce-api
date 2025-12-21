package com.amar.fullstack.ecommerce_api.controller;

import com.amar.fullstack.ecommerce_api.dto.LoginRequest;
import com.amar.fullstack.ecommerce_api.dto.RegisterRequest;
import com.amar.fullstack.ecommerce_api.entities.Role;
import com.amar.fullstack.ecommerce_api.entities.User;
import com.amar.fullstack.ecommerce_api.repository.UserRepository;
import com.amar.fullstack.ecommerce_api.security.JwtService;
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
    private JwtService jwtService;
    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,JwtService jwtService){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtService=jwtService;
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

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest dto){
        User user=userRepository.findByEmail(dto.getEmail());

        if (user==null){
            return "User not Found";

        }

        if (!passwordEncoder.matches(dto.getPassword(),user.getPassword())){
            return "Incorrect Password";
        }

        String token=jwtService.generateToken(user.getEmail(),user.getRole().name());
        return token;
    }

}
