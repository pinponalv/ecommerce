package com.example.ecommerce_backend.order.service;

import com.example.ecommerce_backend.order.dto.CreateOrderRequestDTO;
import com.example.ecommerce_backend.order.dto.OrderResponseDTO;
import com.example.ecommerce_backend.order.dto.UpdateOrderRequestDTO;

import java.util.List;

public interface IOrderService {
    OrderResponseDTO createOrder(Long userId,CreateOrderRequestDTO createOrderRequestDTO);
    OrderResponseDTO updateOrder(Long id, UpdateOrderRequestDTO updateOrderRequestDTO);
    List<OrderResponseDTO> getAllOrders();
    OrderResponseDTO getOrderById(Long id);
    void deleteOrder(Long id);
}
