package com.example.ecommerce_backend.user.controller;

import com.example.ecommerce_backend.user.dto.CreateUserRequestDTO;
import com.example.ecommerce_backend.user.dto.UpdateUserRequestDTO;
import com.example.ecommerce_backend.user.dto.UserResponseDTO;
import com.example.ecommerce_backend.user.entity.User;
import com.example.ecommerce_backend.user.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Product", description = "Product API Operations")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody CreateUserRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable  Long id, @Valid @RequestBody UpdateUserRequestDTO requestDTO) {
        UserResponseDTO userUpdated = userService.updateUser(id, requestDTO);
        return ResponseEntity.ok(userUpdated);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
