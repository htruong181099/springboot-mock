package com.hoangtm14.spring.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_product")
public class OrderProduct extends AuditedEntity {
    @Id
    private UUID id;
    private UUID orderId;
    private UUID productId;
    private int quantity;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.LAZY)
    private Product product;
}
