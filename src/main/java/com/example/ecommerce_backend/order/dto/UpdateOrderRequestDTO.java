package com.example.ecommerce_backend.order.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UpdateOrderRequestDTO {
    @NotNull
    private List<CreateOrderItemRequestDTO> items;
}
