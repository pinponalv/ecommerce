package com.example.ecommerce_backend.payment.service;

import com.example.ecommerce_backend.order.entity.Order;
import com.example.ecommerce_backend.order.entity.enums.OrderStatus;
import com.example.ecommerce_backend.order.repository.IOrderRepository;
import com.example.ecommerce_backend.payment.dto.CreatePaymentRequestDTO;
import com.example.ecommerce_backend.payment.dto.PaymentResponseDTO;
import com.example.ecommerce_backend.payment.dto.UpdatePaymentRequestDTO;
import com.example.ecommerce_backend.payment.entity.Payment;
import com.example.ecommerce_backend.payment.entity.enums.PaymentStatus;
import com.example.ecommerce_backend.payment.repository.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private IPaymentRepository paymentRepository;
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public List<PaymentResponseDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();

        List<PaymentResponseDTO> paymentResponseDTOS = new ArrayList<>();

        for (Payment payment : payments) {
            PaymentResponseDTO dto = new PaymentResponseDTO(
                    payment.getId(),
                    payment.getOrder().getId(),
                    payment.getPaymentStatus(),
                    payment.getAmount(),
                    payment.getMethod(),
                    payment.getTransactionId(),
                    payment.getPaidAt()
            );

            paymentResponseDTOS.add(dto);
        }
        return paymentResponseDTOS;
    }

    @Override
    public PaymentResponseDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));

        return new PaymentResponseDTO(
                payment.getId(),
                payment.getOrder().getId(),
                payment.getPaymentStatus(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getTransactionId(),
                payment.getPaidAt()
        );
    }

    @Override
    public PaymentResponseDTO createPayment(Long orderId, CreatePaymentRequestDTO createPaymentRequestDTO) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        if(paymentRepository.existsByOrderId(orderId)) {
            throw  new RuntimeException("Order already paid");
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setPaymentStatus(PaymentStatus.APPROVED);
        payment.setMethod(createPaymentRequestDTO.getMethod());

        Payment savedPayment = paymentRepository.save(payment);

        order.setStatus(OrderStatus.PAID);

        return new  PaymentResponseDTO(
                savedPayment.getId(),
                savedPayment.getOrder().getId(),
                savedPayment.getPaymentStatus(),
                savedPayment.getAmount(),
                savedPayment.getMethod(),
                savedPayment.getTransactionId(),
                savedPayment.getPaidAt()
        );
    }

    @Override
    public PaymentResponseDTO updatePayment(Long id,UpdatePaymentRequestDTO updatePaymentRequestDTO) {
        Payment existingPayment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));

        existingPayment.setPaymentStatus(updatePaymentRequestDTO.getPaymentStatus());

        Payment updatedPayment = paymentRepository.save(existingPayment);
        return new  PaymentResponseDTO(
                updatedPayment.getId(),
                updatedPayment.getOrder().getId(),
                updatedPayment.getPaymentStatus(),
                updatedPayment.getAmount(),
                updatedPayment.getMethod(),
                updatedPayment.getTransactionId(),
                updatedPayment.getPaidAt()
        );
    }

    @Override
    public void deletePaymentById(Long id) {
        paymentRepository.deleteById(id);
    }
}
