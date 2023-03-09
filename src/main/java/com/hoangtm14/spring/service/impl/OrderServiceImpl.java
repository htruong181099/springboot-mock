package com.hoangtm14.spring.service.impl;

import com.hoangtm14.spring.repository.OrderRepository;
import com.hoangtm14.spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
}
