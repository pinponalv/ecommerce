package com.example.ecommerce_backend.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItemResponseDTO {
    private Long id;
    private Long productId;
    private String productName;
    private int quantity;
    private BigDecimal priceAtPurchase;
    private BigDecimal totalAmount;
}
