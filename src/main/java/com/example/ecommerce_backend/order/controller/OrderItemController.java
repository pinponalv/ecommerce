package com.example.ecommerce_backend.order.controller;

import com.example.ecommerce_backend.order.dto.CreateOrderItemRequestDTO;
import com.example.ecommerce_backend.order.dto.OrderItemResponseDTO;
import com.example.ecommerce_backend.order.dto.UpdateOrderItemRequestDTO;
import com.example.ecommerce_backend.order.entity.OrderItem;
import com.example.ecommerce_backend.order.service.IOrderItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItems")
@Tag(name = "Order Item", description = "Order Item API Operations")
public class OrderItemController {
    @Autowired
    private IOrderItemService orderItemService;

    @PostMapping("/order/{orderId}")
    public ResponseEntity<OrderItemResponseDTO> createOrderItem(@PathVariable Long orderId, @Valid @RequestBody CreateOrderItemRequestDTO createOrderItemRequestDTO){
        OrderItemResponseDTO orderItemResponseDTO = orderItemService.createOrderItem(orderId, createOrderItemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemResponseDTO>> getAllOrderItems() {
        List<OrderItemResponseDTO> allOrderItems = orderItemService.getALlOrderItems();
        return ResponseEntity.ok(allOrderItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponseDTO> getOrderItem(@PathVariable Long id){
        OrderItemResponseDTO orderItemResponseDTO = orderItemService.getOrderItem(id);
        return ResponseEntity.ok(orderItemResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderItemResponseDTO> deleteOrderItem(@PathVariable Long id){
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderItemResponseDTO> updatedOrderItem(@PathVariable Long id, @Valid @RequestBody UpdateOrderItemRequestDTO updateOrderItemRequestDTO){
        OrderItemResponseDTO orderItemResponseDTO = orderItemService.updateOrderItem(id, updateOrderItemRequestDTO);
        return ResponseEntity.ok(orderItemResponseDTO);
    }
}
