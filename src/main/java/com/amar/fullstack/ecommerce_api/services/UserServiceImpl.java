package com.amar.fullstack.ecommerce_api.services;

import com.amar.fullstack.ecommerce_api.entities.User;
import com.amar.fullstack.ecommerce_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
