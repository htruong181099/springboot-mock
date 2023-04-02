package com.hoangtm14.spring.controller;

import com.hoangtm14.spring.constants.Constants;
import com.hoangtm14.spring.model.dto.request.CreateOrderRequest;
import com.hoangtm14.spring.security.SecurityUtil;
import com.hoangtm14.spring.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@Slf4j
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    private ResponseEntity<?> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        UUID userId = SecurityUtil.getCurrentUserId();
        orderService.createOrder(userId, request.getItems());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/order")
    private ResponseEntity<?> getOrderList() {
        log.info("getOrderList" + Constants.BEGIN_API);
        try {
            UUID userId = SecurityUtil.getCurrentUserId();
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderList(userId));
        } finally {
            log.info("getOrderList" + Constants.END_API);
        }
    }

    @GetMapping("/product/{productCode}/order")
    private ResponseEntity<?> getOrderByProduct(@PathVariable("productCode") String productCode) {
        log.info("getOrderList" + Constants.BEGIN_API);
        try {
            UUID userId = SecurityUtil.getCurrentUserId();
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderByProductCode(productCode));
        } finally {
            log.info("getOrderList" + Constants.END_API);
        }
    }
}
