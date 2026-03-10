package com.example.ecommerce_backend.user.service;

import com.example.ecommerce_backend.user.dto.CreateUserRequestDTO;
import com.example.ecommerce_backend.user.dto.UpdateUserRequestDTO;
import com.example.ecommerce_backend.user.dto.UserResponseDTO;
import java.util.List;

public interface IUserService {
    UserResponseDTO createUser(CreateUserRequestDTO requestDTO);
    UserResponseDTO updateUser(Long id, UpdateUserRequestDTO requestDTO);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    void deleteUserById(Long id);
}
