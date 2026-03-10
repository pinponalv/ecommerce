package com.example.ecommerce_backend.user.mapper;

import com.example.ecommerce_backend.user.dto.UserResponseDTO;
import com.example.ecommerce_backend.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toUserResponseDTO(User user);
    List<UserResponseDTO> toDtoList(List<User> users);
}
