package com.amar.fullstack.ecommerce_api.services;

import com.amar.fullstack.ecommerce_api.dto.UserRequestDto;
import com.amar.fullstack.ecommerce_api.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto save(UserRequestDto dto);

    List<UserResponseDto> getAllUsers();
}
