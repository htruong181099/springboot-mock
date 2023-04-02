package com.hoangtm14.spring.service.impl;

import com.hoangtm14.spring.constants.Constants;
import com.hoangtm14.spring.exception.NotFoundException;
import com.hoangtm14.spring.mapper.ProductMapper;
import com.hoangtm14.spring.model.dto.request.CreateProductRequest;
import com.hoangtm14.spring.model.dto.request.UpdateProductRequest;
import com.hoangtm14.spring.model.entity.Product;
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

    @Autowired
    private ProductMapper productMapper;

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
    public Product createProduct(CreateProductRequest request, String thumbnailUrl) {
        log.info("createProduct" + Constants.BEGIN_SERVICE);
        try {
            Product product = Product.builder()
                    .code(request.getCode())
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(BigDecimal.valueOf(50000))
                    .thumbnail(thumbnailUrl)
                    .build();
            return productRepository.save(product);
        } finally {
            log.info("createProduct" + Constants.END_SERVICE);
        }
    }

    @Override
    public void updateProduct(UUID productId, UpdateProductRequest request, String thumbnailUrl) {
        log.info("updateProduct" + Constants.BEGIN_SERVICE);
        try {
            Optional<Product> product = productRepository.findById(productId);
            if (!product.isPresent()) {
                throw new NotFoundException();
            }
            Product updatedProduct = product.get();
            updatedProduct.setCode(request.getCode());
            updatedProduct.setName(request.getName());
            updatedProduct.setDescription(request.getDescription());
            updatedProduct.setPrice(request.getPrice());
            if (thumbnailUrl != null) {
                updatedProduct.setThumbnail(thumbnailUrl);
            }

            productRepository.save(updatedProduct);

        } finally {
            log.info("updateProduct" + Constants.END_SERVICE);
        }
    }

    @Override
    public void deleteProduct(UUID productId) {
        log.info("deleteProduct" + Constants.BEGIN_SERVICE);
        try {
            productRepository.softDeleteProduct(productId);
        } finally {
            log.info("deleteProduct" + Constants.END_SERVICE);
        }
    }

    @Override
    public void deleteProductPermanently(UUID productId) {
        log.info("deleteProductPermanently" + Constants.BEGIN_SERVICE);
        try {
            productRepository.deleteById(productId);
        } finally {
            log.info("deleteProductPermanently" + Constants.END_SERVICE);
        }
    }

    @Override
    public List<Product> getAll() {
        log.info("getAll" + Constants.BEGIN_SERVICE);
        try {
            return productMapper.findAll();
        } finally {
            log.info("getAll" + Constants.END_SERVICE);
        }
    }
}
