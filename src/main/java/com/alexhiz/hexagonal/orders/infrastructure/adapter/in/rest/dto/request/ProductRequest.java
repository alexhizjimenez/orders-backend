package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String description;
    private Double price;
}
