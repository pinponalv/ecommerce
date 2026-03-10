package com.example.ecommerce_backend.user.dto;

import com.example.ecommerce_backend.user.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDTO {


    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;

}
