package com.example.ecommerce_backend.payment.dto;

import com.example.ecommerce_backend.payment.entity.enums.PaymentMethod;
import com.example.ecommerce_backend.payment.entity.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentResponseDTO {
    private Long id;
    private Long orderId;
    private PaymentStatus paymentStatus;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private UUID transactionId;
    private LocalDateTime paidAt;
}
