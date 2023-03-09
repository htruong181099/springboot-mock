package com.hoangtm14.spring.controller;

import com.hoangtm14.spring.model.entity.Product;
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
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductDetail(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(productService.getProduct(UUID.fromString(id)));
    }

    @PostMapping("")
    public ResponseEntity<Product> newProduct() {
        productService.createProduct();
        return ResponseEntity.ok().body(null);
    }
}
