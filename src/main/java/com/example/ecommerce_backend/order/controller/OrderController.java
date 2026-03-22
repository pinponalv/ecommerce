package com.example.ecommerce_backend.order.controller;

import com.example.ecommerce_backend.order.dto.CreateOrderRequestDTO;
import com.example.ecommerce_backend.order.dto.OrderResponseDTO;
import com.example.ecommerce_backend.order.dto.UpdateOrderRequestDTO;
import com.example.ecommerce_backend.order.service.IOrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order", description = "Order API Operations")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<OrderResponseDTO> createOrder(@PathVariable Long userId, @Valid @RequestBody CreateOrderRequestDTO createOrderRequestDTO) {
        OrderResponseDTO orderResponseDTO = orderService.createOrder(userId, createOrderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orderResponseDTOS = orderService.getAllOrders();
        return ResponseEntity.ok(orderResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long id) {
        OrderResponseDTO orderResponseDTO = orderService.getOrderById(id);
        return ResponseEntity.ok(orderResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    //updated all data
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long id, @Valid @RequestBody UpdateOrderRequestDTO updateOrderRequestDTO) {
        OrderResponseDTO orderResponseDTO = orderService.updateOrder(id, updateOrderRequestDTO);
        return ResponseEntity.ok(orderResponseDTO);
    }

}
