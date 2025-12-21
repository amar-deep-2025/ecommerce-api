package com.amar.fullstack.ecommerce_api.controller;


import com.amar.fullstack.ecommerce_api.dto.UserRequestDto;
import com.amar.fullstack.ecommerce_api.dto.UserResponseDto;
import com.amar.fullstack.ecommerce_api.entities.User;
import com.amar.fullstack.ecommerce_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping
    public List<UserResponseDto> getAll(){
        //This
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        System.out.println("USER: " + auth.getName());
        System.out.println("ROLES: " + auth.getAuthorities());
        return userService.getAllUsers();
    }

    @PostMapping
    public UserResponseDto create(@Valid @RequestBody UserRequestDto dto){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        //This log is for testing purpose to see which user is creating new user
        System.out.println("USER: " + auth.getName());
        System.out.println("ROLES: " + auth.getAuthorities());
        System.out.println("Insert New User "+dto.getName());
        return userService.save(dto);
    }



}
