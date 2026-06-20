package com.alexhiz.hexagonal.orders.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    private Product product;
    private Integer quantity;
    private Double unitPrice;
}
