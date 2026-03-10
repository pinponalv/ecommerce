package com.example.ecommerce_backend.payment.service;

import com.example.ecommerce_backend.payment.dto.CreatePaymentRequestDTO;
import com.example.ecommerce_backend.payment.dto.PaymentResponseDTO;
import com.example.ecommerce_backend.payment.dto.UpdatePaymentRequestDTO;

import java.util.List;

public interface IPaymentService {
    List<PaymentResponseDTO> getAllPayments();
    PaymentResponseDTO getPaymentById(Long id);
    PaymentResponseDTO createPayment(CreatePaymentRequestDTO createPaymentRequestDTO);
    PaymentResponseDTO updatePayment(Long id, UpdatePaymentRequestDTO updatePaymentRequestDTO);
    void deletePaymentById(Long id);

}
