package com.example.ecommerce_backend.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserRequestDTO {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
