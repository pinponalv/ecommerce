package com.example.ecommerce_backend.order.service;

import com.example.ecommerce_backend.order.dto.CreateOrderItemRequestDTO;
import com.example.ecommerce_backend.order.dto.OrderItemResponseDTO;
import com.example.ecommerce_backend.order.dto.UpdateOrderItemRequestDTO;

import java.util.List;

public interface IOrderItemService {
    OrderItemResponseDTO createOrderItem(Long orderId, CreateOrderItemRequestDTO createOrderItemRequestDTO);
    OrderItemResponseDTO updateOrderItem(Long id, UpdateOrderItemRequestDTO updateOrderItemRequestDTO);
    List<OrderItemResponseDTO> getALlOrderItems();
    OrderItemResponseDTO getOrderItem(Long id);
    void deleteOrderItem(Long id);
}
