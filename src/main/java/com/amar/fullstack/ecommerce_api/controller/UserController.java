package com.amar.fullstack.ecommerce_api.controller;


import com.amar.fullstack.ecommerce_api.dto.UserRequestDto;
import com.amar.fullstack.ecommerce_api.dto.UserResponseDto;
import com.amar.fullstack.ecommerce_api.entities.User;
import com.amar.fullstack.ecommerce_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping
    public List<UserResponseDto> getAll(){
        return userService.getAllUsers();
    }

    @PostMapping
    public UserResponseDto create(@Valid @RequestBody UserRequestDto dto){
        return userService.save(dto);
    }



}
