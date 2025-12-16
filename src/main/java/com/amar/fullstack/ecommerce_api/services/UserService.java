package com.amar.fullstack.ecommerce_api.services;

import com.amar.fullstack.ecommerce_api.entities.User;

import java.util.List;


public interface UserService {
    User save(User user);

    List<User> getAllUsers();
}
