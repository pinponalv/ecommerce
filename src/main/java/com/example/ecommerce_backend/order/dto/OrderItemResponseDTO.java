package com.example.ecommerce_backend.order.dto;

import java.math.BigDecimal;

public class OrderItemResponseDTO {
    private Long id;
    private Long productId;
    private String productName;
    private int quantity;
    private BigDecimal priceAtPurchase;
    private BigDecimal totalAmount;
}
