package com.example.ecommerce_backend.user.service;

import com.example.ecommerce_backend.user.dto.CreateUserRequestDTO;
import com.example.ecommerce_backend.user.dto.UpdateUserRequestDTO;
import com.example.ecommerce_backend.user.dto.UserResponseDTO;
import com.example.ecommerce_backend.user.entity.User;
import com.example.ecommerce_backend.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserResponseDTO createUser(CreateUserRequestDTO requestDTO) {
        User user = new User();
        user.setFirstName(requestDTO.getFirstName());
        user.setLastName(requestDTO.getLastName());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());
        user.setRole(requestDTO.getRole());

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    @Override
    public UserResponseDTO updateUser(Long id, UpdateUserRequestDTO requestDTO) {
        //search id
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setFirstName(requestDTO.getFirstName());
        existingUser.setLastName(requestDTO.getLastName());
        existingUser.setEmail(requestDTO.getEmail());
        existingUser.setPassword(requestDTO.getPassword());

        User savedUser = userRepository.save(existingUser);
        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );

    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        //get entities of repository
        List<User> users = userRepository.findAll();
        //convert dto
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();

        for (User user : users) {
            UserResponseDTO dto = new UserResponseDTO(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getRole()
            );
            userResponseDTOS.add(dto);
        }

        //return
        return userResponseDTOS;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        //get entity of repository
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        //return and convert dto
        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
