package com.hoangtm14.spring.service.impl;

import com.hoangtm14.spring.constants.Constants;
import com.hoangtm14.spring.model.dto.OrderItemDTO;
import com.hoangtm14.spring.model.entity.Order;
import com.hoangtm14.spring.model.entity.OrderProduct;
import com.hoangtm14.spring.model.entity.Product;
import com.hoangtm14.spring.repository.OrderProductRepository;
import com.hoangtm14.spring.repository.OrderRepository;
import com.hoangtm14.spring.repository.ProductRepository;
import com.hoangtm14.spring.service.OrderService;
import com.hoangtm14.spring.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void createOrder(UUID userId, List<OrderItemDTO> createOrderRequestList) {
        UUID orderId = UUID.randomUUID();

        List<String> productCodeList = createOrderRequestList.stream().map(OrderItemDTO::getCode).collect(Collectors.toList());
        List<Product> productList = productRepository.findByCodeIn(productCodeList);

        Map<String, Product> productPriceMap = productList.stream().collect(Collectors.toMap(
                Product::getCode, product -> product
        ));

        BigDecimal totalAmount = BigDecimal.ZERO;

        List<OrderProduct> orderProductList = new ArrayList<>();
        for (OrderItemDTO dto : createOrderRequestList) {
            OrderProduct orderProduct = new OrderProduct();
            Product product = productPriceMap.get(dto.getCode());
            orderProduct.setProductId(product.getId());
            orderProduct.setQuantity(dto.getQuantity());
            orderProduct.setOrderId(orderId);
            BigDecimal amount = product.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity()));
            orderProduct.setAmount(amount);
            orderProductList.add(orderProduct);

            totalAmount = totalAmount.add(amount);
        }

        Order order = Order.builder()
                .id(orderId)
                .userId(userId)
                .orderDate(DateTimeUtil.getCurrentTime())
                .totalAmount(totalAmount)
//                .orderProductCollection(orderProductList)
                .build();

        orderRepository.save(order);
        orderProductRepository.saveAll(orderProductList);
    }

    @Override
    public List<Order> getOrderList(UUID userId) {
        log.info("getOrderList" + Constants.BEGIN_SERVICE);
        try {
            return orderRepository.findByUserId(userId);
        } finally {
            log.info("getOrderList" + Constants.END_SERVICE);
        }
    }

    @Override
    public List<Order> getOrderByProductCode(String productCode) {
        log.info("getOrderList" + Constants.BEGIN_SERVICE);
        try {
            return orderRepository.findByProductListProductCode(productCode);
        } finally {
            log.info("getOrderList" + Constants.END_SERVICE);
        }
    }
}
