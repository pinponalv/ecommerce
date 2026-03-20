package com.example.ecommerce_backend.order.service;

import com.example.ecommerce_backend.order.dto.CreateOrderItemRequestDTO;
import com.example.ecommerce_backend.order.dto.OrderItemResponseDTO;
import com.example.ecommerce_backend.order.dto.UpdateOrderItemRequestDTO;
import com.example.ecommerce_backend.order.entity.Order;
import com.example.ecommerce_backend.order.entity.OrderItem;
import com.example.ecommerce_backend.order.repository.IOrderItemRepository;
import com.example.ecommerce_backend.order.repository.IOrderRepository;
import com.example.ecommerce_backend.product.entity.Product;
import com.example.ecommerce_backend.product.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderItemService implements IOrderItemService {
    @Autowired
    private IOrderItemRepository orderItemRepository;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IProductRepository productRepository;
    @Override
    public OrderItemResponseDTO createOrderItem(Long orderId, CreateOrderItemRequestDTO createOrderItemRequestDTO) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found"));

        Product product = productRepository.findById(createOrderItemRequestDTO.getProductId())
                .orElseThrow(()-> new RuntimeException("Product not found"));

        //create orderItem
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(createOrderItemRequestDTO.getQuantity());
        orderItem.setPriceAtPurchase(product.getPrice());

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        BigDecimal subtotal = savedOrderItem.getPriceAtPurchase().multiply(BigDecimal.valueOf(savedOrderItem.getQuantity()));

        //create response
        OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();
        orderItemResponseDTO.setId(savedOrderItem.getId());
        orderItemResponseDTO.setProductId(savedOrderItem.getProduct().getId());
        orderItemResponseDTO.setProductName(savedOrderItem.getProduct().getName());
        orderItemResponseDTO.setQuantity(savedOrderItem.getQuantity());
        orderItemResponseDTO.setPriceAtPurchase(savedOrderItem.getPriceAtPurchase());
        orderItemResponseDTO.setTotalAmount(subtotal);
        return orderItemResponseDTO;
    }

    @Override
    public OrderItemResponseDTO updateOrderItem(Long id, UpdateOrderItemRequestDTO updateOrderItemRequestDTO) {
        return null;
    }

    @Override
    public List<OrderItemResponseDTO> getALlOrderItems() {
        return List.of();
    }

    @Override
    public OrderItemResponseDTO getOrderItem(Long id) {
        return null;
    }

    @Override
    public void deleteOrderItem(Long id) {

    }
}
