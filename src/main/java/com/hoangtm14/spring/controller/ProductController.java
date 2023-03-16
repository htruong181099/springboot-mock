package com.hoangtm14.spring.controller;

import com.hoangtm14.spring.constants.Constants;
import com.hoangtm14.spring.model.dto.request.CreateProductRequest;
import com.hoangtm14.spring.model.dto.request.UpdateProductRequest;
import com.hoangtm14.spring.model.entity.Product;
import com.hoangtm14.spring.service.ProductService;
import com.hoangtm14.spring.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UploadService uploadService;

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

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> createProduct(@Valid @ModelAttribute CreateProductRequest request) {
        log.info("createProduct" + Constants.BEGIN_API);
        try {
            String thumbnailUrl = uploadService.uploadFile(request.getThumbnail());
            Product product = productService.createProduct(request, thumbnailUrl);
            return ResponseEntity.ok().body(product);
        } finally {
            log.info("createProduct" + Constants.END_API);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String productId,
                                                 @Valid @ModelAttribute UpdateProductRequest request) {
        log.info("updateProduct" + Constants.BEGIN_API);
        try {
            String thumbnailUrl = null;
            if (!request.getThumbnail().isEmpty()) {
                thumbnailUrl = uploadService.uploadFile(request.getThumbnail());
            }
            productService.updateProduct(UUID.fromString(productId), request, thumbnailUrl);
            return ResponseEntity.ok().body(null);
        } finally {
            log.info("updateProduct" + Constants.END_API);
        }
    }
}
