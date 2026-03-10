package com.example.ecommerce_backend.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class CreateOrderRequestDTO {
    @NotNull
    private List<CreateOrderItemRequestDTO> items;
}
