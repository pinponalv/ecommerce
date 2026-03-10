package com.example.ecommerce_backend.product.service;

import com.example.ecommerce_backend.product.dto.CreateProductRequestDTO;
import com.example.ecommerce_backend.product.dto.ProductResponseDTO;
import com.example.ecommerce_backend.product.dto.UpdateProductRequestDTO;
import com.example.ecommerce_backend.product.entity.Product;
import com.example.ecommerce_backend.product.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductResponseDTO createProduct(CreateProductRequestDTO createProductRequestDTO) {
        Product product = new Product();
        product.setName(createProductRequestDTO.getName());
        product.setDescription(createProductRequestDTO.getDescription());
        product.setPrice(createProductRequestDTO.getPrice());
        product.setStock(createProductRequestDTO.getStock());

        Product savedProduct = productRepository.save(product);

        return new ProductResponseDTO(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getPrice(),
                savedProduct.getStock()
        );
    }

    @Override
    public ProductResponseDTO updateProduct(Long id,UpdateProductRequestDTO updateProductRequestDTO) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(updateProductRequestDTO.getName());
        existingProduct.setDescription(updateProductRequestDTO.getDescription());
        existingProduct.setPrice(updateProductRequestDTO.getPrice());
        existingProduct.setStock(updateProductRequestDTO.getStock());
        return null;
    }

    @Override
    public List<ProductResponseDTO> findAllProducts() {
        return List.of();
    }

    @Override
    public ProductResponseDTO findProductById(Long id) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
