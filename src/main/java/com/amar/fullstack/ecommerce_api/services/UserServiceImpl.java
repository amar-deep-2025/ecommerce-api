package com.amar.fullstack.ecommerce_api.services;

import com.amar.fullstack.ecommerce_api.dto.UserRequestDto;
import com.amar.fullstack.ecommerce_api.dto.UserResponseDto;
import com.amar.fullstack.ecommerce_api.entities.Role;
import com.amar.fullstack.ecommerce_api.entities.User;
import com.amar.fullstack.ecommerce_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }


    @Override
    public UserResponseDto save(UserRequestDto dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.USER);

        User saved = userRepository.save(user);

        UserResponseDto response = new UserResponseDto();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setEmail(saved.getEmail());
        response.setRole(saved.getRole().name());

        return response;
    }


    @Override
    public List<UserResponseDto> getAllUsers() {

        return userRepository.findAll().stream().map(user -> {
            UserResponseDto dto = new UserResponseDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
//            dto.setPassword(user.getPassword());
            dto.setRole(user.getRole().name());
            return dto;
        }).toList();
    }

    @Override
    public UserResponseDto getById(Long id) {
        User user=userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found"));

        UserResponseDto dto= new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());
        return dto;
    }

    @Override
    public UserResponseDto update(Long id, UserResponseDto dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update role only if provided
        if (dto.getRole() != null) {
            user.setRole(Role.valueOf(dto.getRole()));
        }

        User updated = userRepository.save(user);

        // convert to response DTO
        UserResponseDto res = new UserResponseDto();
        res.setId(updated.getId());
        res.setName(updated.getName());
        res.setEmail(updated.getEmail());
        res.setRole(updated.getRole().name());

        return res;
    }

}
