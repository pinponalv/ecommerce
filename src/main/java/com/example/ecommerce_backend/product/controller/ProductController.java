package com.example.ecommerce_backend.product.controller;

import com.example.ecommerce_backend.product.dto.CreateProductRequestDTO;
import com.example.ecommerce_backend.product.dto.ProductResponseDTO;
import com.example.ecommerce_backend.product.dto.UpdateProductRequestDTO;
import com.example.ecommerce_backend.product.service.IProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name ="Product", description = "Product API Operations")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody CreateProductRequestDTO createProductRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(createProductRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<ProductResponseDTO> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id){
        ProductResponseDTO productResponseDTO = productService.findProductById(id);
        return ResponseEntity.ok(productResponseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProductById(@PathVariable Long id, @Valid @RequestBody UpdateProductRequestDTO updateProductRequestDTO){
        ProductResponseDTO productResponseDTO = productService.updateProduct(id, updateProductRequestDTO);
        return ResponseEntity.ok(productResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> deleteProductById(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
