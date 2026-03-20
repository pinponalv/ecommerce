package com.example.ecommerce_backend.order.mapper;

import com.example.ecommerce_backend.order.dto.OrderItemResponseDTO;
import com.example.ecommerce_backend.order.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    OrderItemResponseDTO toDTO(OrderItem orderItem);
    List<OrderItemResponseDTO> toDTOList(List<OrderItem> orderItem);
}
