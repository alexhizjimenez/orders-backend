package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.response;

import com.alexhiz.hexagonal.orders.domain.model.Product;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ProductResponse(
    UUID id,
    String description,
    Double price
) {
    public static ProductResponse from(Product product) {
        return  ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
