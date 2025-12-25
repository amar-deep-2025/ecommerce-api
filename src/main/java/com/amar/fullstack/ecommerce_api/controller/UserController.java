package com.amar.fullstack.ecommerce_api.controller;


import com.amar.fullstack.ecommerce_api.dto.UserRequestDto;
import com.amar.fullstack.ecommerce_api.dto.UserResponseDto;
import com.amar.fullstack.ecommerce_api.entities.User;
import com.amar.fullstack.ecommerce_api.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger log =
            LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;



    @GetMapping
    public List<UserResponseDto> getAll(){
        log.info("Get All Users API called");
        //This
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        System.out.println("USER: " + auth.getName());
        System.out.println("ROLES: " + auth.getAuthorities());
        if (auth.getName().isEmpty() || auth.getAuthorities()==null){
            log.warn("UNAUTHORIZED ACCESS to Get All Users API");
        }
        return userService.getAllUsers();
    }

    @PostMapping
    public UserResponseDto create(@Valid @RequestBody UserRequestDto dto){
        log.info("Create New User API called | name={} | email={}",dto.getName(),dto.getEmail());
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        //This log is for testing purpose to see which user is creating new user
        System.out.println("USER: " + auth.getName());
        System.out.println("ROLES: " + auth.getAuthorities());
        System.out.println("Insert New User "+dto.getName());
        if (auth.getName().isEmpty() || auth.getAuthorities()==null){
            log.warn("UNAUTHORIZED ACCESS to Create New User API");
        }
        return userService.save(dto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable Long id){
        log.info("Get User By ID API called | id={}",id);
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id,
                                  @Valid @RequestBody UserResponseDto dto){
        log.info("Update User API called | id={} | name={} | email={}",id,dto.getName(),dto.getEmail());
        return userService.update(id,dto);
    }


}
