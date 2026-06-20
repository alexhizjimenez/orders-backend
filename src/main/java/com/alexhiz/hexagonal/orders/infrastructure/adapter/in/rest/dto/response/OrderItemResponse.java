package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.response;

import com.alexhiz.hexagonal.orders.domain.model.OrderItem;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemResponse {
    private ProductResponse product;
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;

    public static OrderItemResponse from(OrderItem item) {
        if (item == null) {
            return null;
        }
        return OrderItemResponse.builder()
                .product(item.getProduct() != null ? ProductResponse.from(item.getProduct()) : null)
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .subtotal((item.getQuantity() != null && item.getUnitPrice() != null) ? 
                        item.getQuantity() * item.getUnitPrice() : 0.0)
                .build();
    }
}
