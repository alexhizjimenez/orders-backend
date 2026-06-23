package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.response;

import com.alexhiz.hexagonal.orders.domain.model.Order;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
public record OrderResponse(
    UUID id,
    UUID branchId,
    LocalDateTime orderDate,
    Double total,
    List<OrderItemResponse> items
) {
    public static OrderResponse from(Order order) {
        if (order == null) {
            return null;
        }
        return OrderResponse.builder()
                .id(order.getId())
                .branchId(order.getBranchId())
                .orderDate(order.getOrderDate())
                .total(order.getTotal())
                .items(order.getItems() != null ? order.getItems().stream()
                        .map(OrderItemResponse::from)
                        .collect(Collectors.toList()) : new ArrayList<>())
                .build();
    }
}
