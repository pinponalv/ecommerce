package com.example.ecommerce_backend.order.service;

import com.example.ecommerce_backend.order.dto.CreateOrderItemRequestDTO;
import com.example.ecommerce_backend.order.dto.CreateOrderRequestDTO;
import com.example.ecommerce_backend.order.dto.OrderResponseDTO;
import com.example.ecommerce_backend.order.dto.UpdateOrderRequestDTO;
import com.example.ecommerce_backend.order.entity.Order;
import com.example.ecommerce_backend.order.entity.enums.OrderStatus;
import com.example.ecommerce_backend.order.repository.IOrderRepository;
import com.example.ecommerce_backend.product.entity.Product;
import com.example.ecommerce_backend.product.repository.IProductRepository;
import com.example.ecommerce_backend.product.service.IProductService;
import com.example.ecommerce_backend.user.entity.User;
import com.example.ecommerce_backend.user.repository.IUserRepository;
import com.example.ecommerce_backend.user.service.IUserService;
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
    private IProductService productService;

    @Override
    public OrderResponseDTO createOrder(Long userId,CreateOrderRequestDTO createOrderRequestDTO) {
        //search user
        User user = userRepository.findById(userId).orElseThrow( () -> new RuntimeException( "User not found" ) );

        //create order
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        //create order items
        List<CreateOrderItemRequestDTO> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for(CreateOrderItemRequestDTO itemDTO: createOrderRequestDTO.getItems()){
            Product product = productService.findById(itemDTO).orElseThrow(() -> new RuntimeException("Product not found"));


        }

        return null;
    }

    @Override
    public OrderResponseDTO updateOrder(Long id, UpdateOrderRequestDTO updateOrderRequestDTO) {
        return null;
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return List.of();
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }
}
