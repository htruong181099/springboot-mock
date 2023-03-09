package com.hoangtm14.spring.service.impl;

import com.hoangtm14.spring.model.entity.Product;
import com.hoangtm14.spring.repository.ProductRepository;
import com.hoangtm14.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public void createProduct() {
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Thạch rau cau");
        product.setDescription("Ngon vỗn lài");
        product.setPrice(BigDecimal.valueOf(50000));
        productRepository.save(product);
    }
}
