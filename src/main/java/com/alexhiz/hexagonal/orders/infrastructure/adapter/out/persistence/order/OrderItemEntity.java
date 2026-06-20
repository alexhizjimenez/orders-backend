package com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.order;

import com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "order_items")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private Integer quantity;

    private Double unitPrice;
}
