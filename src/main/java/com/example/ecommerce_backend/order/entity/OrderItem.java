package com.example.ecommerce_backend.order.entity;

import com.example.ecommerce_backend.product.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id") //fk
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id") //fk
    private Product product;

    @Positive
    private int quantity;

    @Positive
    private BigDecimal priceAtPurchase;
}
