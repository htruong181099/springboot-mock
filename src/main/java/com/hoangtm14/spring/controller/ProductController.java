package com.hoangtm14.spring.controller;

import com.hoangtm14.spring.constants.Constants;
import com.hoangtm14.spring.model.entity.Product;
import com.hoangtm14.spring.model.request.CreateProductRequest;
import com.hoangtm14.spring.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProducts() {
        log.info("getAllProducts" + Constants.BEGIN_API);
        try {
            return ResponseEntity.ok().body(productService.getAllProducts());
        } finally {
            log.info("getAllProducts" + Constants.END_API);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductDetail(@PathVariable("id") String id) {
        log.info("getProductDetail" + Constants.BEGIN_API);
        try {
            return ResponseEntity.ok().body(productService.getProduct(UUID.fromString(id)));
        } finally {
            log.info("getProductDetail" + Constants.END_API);
        }
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {
        log.info("createProduct" + Constants.BEGIN_API);
        try {
            productService.createProduct(request);
            return ResponseEntity.ok().body(null);
        } finally {
            log.info("createProduct" + Constants.END_API);
        }
    }
}
