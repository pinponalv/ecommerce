package com.example.ecommerce_backend.product.service;

import com.example.ecommerce_backend.product.dto.CreateProductRequestDTO;
import com.example.ecommerce_backend.product.dto.ProductResponseDTO;
import com.example.ecommerce_backend.product.dto.UpdateProductRequestDTO;

import java.util.List;

public interface IProductService {
    ProductResponseDTO createProduct(CreateProductRequestDTO createProductRequestDTO);
    ProductResponseDTO updateProduct(Long id,UpdateProductRequestDTO updateProductRequestDTO);
    List<ProductResponseDTO> findAllProducts();
    ProductResponseDTO findProductById(Long id);
    void deleteProduct(Long id);
}
