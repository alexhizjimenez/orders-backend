package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.response;

import com.alexhiz.hexagonal.orders.domain.model.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ProductResponse {
    private UUID id;
    private String description;
    private Double price;

    public static ProductResponse from(Product product) {
        return  ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
