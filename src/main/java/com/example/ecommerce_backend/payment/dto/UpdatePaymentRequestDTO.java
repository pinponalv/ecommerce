package com.example.ecommerce_backend.payment.dto;

import com.example.ecommerce_backend.payment.entity.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdatePaymentRequestDTO {
    @NotNull
    private PaymentStatus paymentStatus;
}
