package com.example.ecommerce_backend.order.service;

import com.example.ecommerce_backend.order.dto.CreateOrderItemRequestDTO;
import com.example.ecommerce_backend.order.dto.OrderItemResponseDTO;
import com.example.ecommerce_backend.order.dto.UpdateOrderItemRequestDTO;
import com.example.ecommerce_backend.order.entity.Order;
import com.example.ecommerce_backend.order.entity.OrderItem;
import com.example.ecommerce_backend.order.entity.enums.OrderStatus;
import com.example.ecommerce_backend.order.repository.IOrderItemRepository;
import com.example.ecommerce_backend.order.repository.IOrderRepository;
import com.example.ecommerce_backend.product.entity.Product;
import com.example.ecommerce_backend.product.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
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
        OrderItem getOrderItem = orderItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Order Item not found"));

        Order order = getOrderItem.getOrder();

        if(order.getStatus() != OrderStatus.CREATED){
            throw new RuntimeException("Order Status Not Created");
        }

        //changed product
        if(updateOrderItemRequestDTO.getProductId() != null){
            Product product = productRepository.findById(updateOrderItemRequestDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            getOrderItem.setProduct(product);
            getOrderItem.setPriceAtPurchase(product.getPrice());
        }

        //changed quantity
        if(updateOrderItemRequestDTO.getQuantity() > 0){
            getOrderItem.setQuantity(updateOrderItemRequestDTO.getQuantity());
        }

        OrderItem updatedOrderItem = orderItemRepository.save(getOrderItem);

        //calculated subtotal
        BigDecimal subtotal = updatedOrderItem.getPriceAtPurchase().multiply(BigDecimal.valueOf(getOrderItem.getQuantity()));

        OrderItemResponseDTO response = new OrderItemResponseDTO();
                response.setId(updatedOrderItem.getId());
                response.setProductId(updatedOrderItem.getProduct().getId());
                response.setProductName(updatedOrderItem.getProduct().getName());
                response.setQuantity(updatedOrderItem.getQuantity());
                response.setPriceAtPurchase(updatedOrderItem.getPriceAtPurchase());
                response.setTotalAmount(subtotal);


        return response;
    }

    @Override
    public List<OrderItemResponseDTO> getALlOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        List<OrderItemResponseDTO> responseDTOs = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
            OrderItemResponseDTO responseDTO = new OrderItemResponseDTO();
            responseDTO.getId();
            responseDTO.getProductId();
            responseDTO.getProductName();
            responseDTO.getQuantity();
            responseDTO.getPriceAtPurchase();
            responseDTO.getTotalAmount();
            responseDTOs.add(responseDTO);
        }
        return responseDTOs;
    }

    @Override
    public OrderItemResponseDTO getOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Order Item not found"));
        OrderItemResponseDTO responseDTO = new OrderItemResponseDTO();

        responseDTO.setId(orderItem.getId());
        responseDTO.setProductId(orderItem.getProduct().getId());
        responseDTO.setProductName(orderItem.getProduct().getName());
        responseDTO.setQuantity(orderItem.getQuantity());
        responseDTO.setPriceAtPurchase(orderItem.getPriceAtPurchase());
        responseDTO.setTotalAmount(orderItem.getPriceAtPurchase());
        return responseDTO;
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
