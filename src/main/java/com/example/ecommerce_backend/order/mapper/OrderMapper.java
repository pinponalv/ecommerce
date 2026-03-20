package com.example.ecommerce_backend.order.mapper;

import com.example.ecommerce_backend.order.dto.OrderResponseDTO;
import com.example.ecommerce_backend.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    OrderResponseDTO toDTO(Order order);
    List<OrderResponseDTO> toDTOList(List<Order> orders);
}
