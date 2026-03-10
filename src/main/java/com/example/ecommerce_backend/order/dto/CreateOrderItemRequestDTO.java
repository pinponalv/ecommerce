package com.example.ecommerce_backend.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateOrderItemRequestDTO {
    @NotNull
    private Long productId;

    @Positive
    private int quantity;
}
