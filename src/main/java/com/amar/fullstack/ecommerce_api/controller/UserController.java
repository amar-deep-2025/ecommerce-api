package com.amar.fullstack.ecommerce_api.controller;


import com.amar.fullstack.ecommerce_api.entities.User;
import com.amar.fullstack.ecommerce_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User create(@RequestBody User user){
        return userService.save(user);
    }



}
