package com.example.ecommerce_backend.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateProductRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @Positive
    private BigDecimal price;
    @PositiveOrZero
    private int stock;
}
