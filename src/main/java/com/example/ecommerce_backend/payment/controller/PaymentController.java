package com.example.ecommerce_backend.payment.controller;

import com.example.ecommerce_backend.payment.dto.CreatePaymentRequestDTO;
import com.example.ecommerce_backend.payment.dto.PaymentResponseDTO;
import com.example.ecommerce_backend.payment.dto.UpdatePaymentRequestDTO;
import com.example.ecommerce_backend.payment.service.IPaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@Tag(name = "Payment", description = "Payment API Operations")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@Valid @RequestBody CreatePaymentRequestDTO createPaymentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPayment(createPaymentRequestDTO));
    }

    @PatchMapping("/id")
    public ResponseEntity<PaymentResponseDTO> updatePayment(@PathVariable  Long id,@Valid @RequestBody UpdatePaymentRequestDTO updatePaymentRequestDTO){
        PaymentResponseDTO paymentResponseDTO = paymentService.updatePayment(id, updatePaymentRequestDTO);
        return ResponseEntity.ok(paymentResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments(){
        List<PaymentResponseDTO> paymentList = paymentService.getAllPayments();
        return ResponseEntity.ok(paymentList);
    }

    @GetMapping("/id")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable  Long id){
        PaymentResponseDTO payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @DeleteMapping("/id")
    public ResponseEntity<PaymentResponseDTO> deletePaymentById(@PathVariable  Long id){
        paymentService.deletePaymentById(id);
        return ResponseEntity.noContent().build();
    }


}
