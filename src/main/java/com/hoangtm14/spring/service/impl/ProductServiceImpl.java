package com.hoangtm14.spring.service.impl;

import com.hoangtm14.spring.constants.Constants;
import com.hoangtm14.spring.exception.NotFoundException;
import com.hoangtm14.spring.model.entity.Product;
import com.hoangtm14.spring.model.request.CreateProductRequest;
import com.hoangtm14.spring.repository.ProductRepository;
import com.hoangtm14.spring.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        log.info("getAllProducts" + Constants.BEGIN_SERVICE);
        try {
            return productRepository.findAll();
        } finally {
            log.info("getAllProducts" + Constants.END_SERVICE);
        }
    }

    @Override
    public Product getProduct(UUID id) {
        log.info("getProduct" + Constants.BEGIN_SERVICE);
        try {
            Optional<Product> product = productRepository.findById(id);
            if (!product.isPresent()) {
                throw new NotFoundException();
            }
            return product.get();
        } finally {
            log.info("getProduct" + Constants.END_SERVICE);
        }
    }

    @Override
    public void createProduct(CreateProductRequest request) {
        log.info("createProduct" + Constants.BEGIN_SERVICE);
        try {
            Product product = Product.builder()
                    .id(UUID.randomUUID())
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(BigDecimal.valueOf(50000))
                    .build();
            productRepository.save(product);
        } finally {
            log.info("createProduct" + Constants.END_SERVICE);
        }
    }
}
