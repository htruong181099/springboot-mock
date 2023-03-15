package com.hoangtm14.spring.service;

import com.hoangtm14.spring.model.dto.request.CreateProductRequest;
import com.hoangtm14.spring.model.dto.request.UpdateProductRequest;
import com.hoangtm14.spring.model.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProduct(UUID id);

    void createProduct(CreateProductRequest request);

    void updateProduct(UUID productId, UpdateProductRequest request);

    void deleteProduct(UUID productId);
}
