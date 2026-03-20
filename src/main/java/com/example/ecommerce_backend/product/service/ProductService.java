package com.example.ecommerce_backend.product.service;

import com.example.ecommerce_backend.product.dto.CreateProductRequestDTO;
import com.example.ecommerce_backend.product.dto.ProductResponseDTO;
import com.example.ecommerce_backend.product.dto.UpdateProductRequestDTO;
import com.example.ecommerce_backend.product.entity.Product;
import com.example.ecommerce_backend.product.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

        Product updatedProduct = productRepository.save(existingProduct);
        return new ProductResponseDTO(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getDescription(),
                updatedProduct.getPrice(),
                updatedProduct.getStock()
        );
    }

    @Override
    public List<ProductResponseDTO> findAllProducts() {
        List<Product> products = productRepository.findAll();
        //convert dto
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

        for (Product product : products) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock()
            );
        }
        return productResponseDTOS;
    }

    @Override
    public ProductResponseDTO findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        return new  ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
