package com.hoangtm14.spring.service;

import com.hoangtm14.spring.model.dto.OrderItemDTO;
import com.hoangtm14.spring.model.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    void createOrder(UUID userId, List<OrderItemDTO> request);

    List<Order> getOrderList(UUID userId);

    List<Order> getOrderByProductCode(String productCode);
}
