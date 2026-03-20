package com.example.ecommerce_backend.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateOrderRequestDTO {
    @NotNull
    private List<CreateOrderItemRequestDTO> items;
}
