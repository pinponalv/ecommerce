package com.example.ecommerce_backend.order.service;

import com.example.ecommerce_backend.order.dto.CreateOrderItemRequestDTO;
import com.example.ecommerce_backend.order.dto.CreateOrderRequestDTO;
import com.example.ecommerce_backend.order.dto.OrderResponseDTO;
import com.example.ecommerce_backend.order.dto.UpdateOrderRequestDTO;
import com.example.ecommerce_backend.order.entity.Order;
import com.example.ecommerce_backend.order.entity.OrderItem;
import com.example.ecommerce_backend.order.entity.enums.OrderStatus;
import com.example.ecommerce_backend.order.mapper.OrderMapper;
import com.example.ecommerce_backend.order.repository.IOrderRepository;
import com.example.ecommerce_backend.product.entity.Product;
import com.example.ecommerce_backend.product.repository.IProductRepository;
import com.example.ecommerce_backend.product.service.IProductService;
import com.example.ecommerce_backend.user.entity.User;
import com.example.ecommerce_backend.user.repository.IUserRepository;
import com.example.ecommerce_backend.user.service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IProductRepository productRepository ;

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    @Override
    public OrderResponseDTO createOrder(Long userId,CreateOrderRequestDTO createOrderRequestDTO) {
        //search user
        User user = userRepository.findById(userId).orElseThrow( () -> new RuntimeException( "User not found" ) );

        if(createOrderRequestDTO.getItems() == null || createOrderRequestDTO.getItems().isEmpty()){
            throw new RuntimeException("Order must contain items");
        }

        //create order
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        //create order items
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for(CreateOrderItemRequestDTO itemDTO: createOrderRequestDTO.getItems()){
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow( () -> new RuntimeException( "Product not found" ) );

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPriceAtPurchase(product.getPrice());

            BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));

            total = total.add(subtotal);
            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);



        return orderMapper.toDTO(savedOrder);
    }

    @Override
    public OrderResponseDTO updateOrder(Long id, UpdateOrderRequestDTO updateOrderRequestDTO) {
        Order order = orderRepository.findById(id).orElseThrow( () -> new RuntimeException( "Order not found" ) );

        if(order.getStatus() != OrderStatus.CREATED){
            throw new RuntimeException( "Only orders in created status can be updated" );
        }

        if(updateOrderRequestDTO.getItems() == null || updateOrderRequestDTO.getItems().isEmpty()){
            throw new RuntimeException("Order must contain items");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for(CreateOrderItemRequestDTO itemDTO: updateOrderRequestDTO.getItems()){
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow( () -> new RuntimeException( "Product not found" ) );

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPriceAtPurchase(product.getPrice());

            BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));

            total = total.add(subtotal);

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        Order updatedOrder = orderRepository.save(order);

        return orderMapper.toDTO(updatedOrder);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(orderMapper::toDTO).toList();
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow( () -> new RuntimeException( "Order not found" ) );
        return orderMapper.toDTO(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
