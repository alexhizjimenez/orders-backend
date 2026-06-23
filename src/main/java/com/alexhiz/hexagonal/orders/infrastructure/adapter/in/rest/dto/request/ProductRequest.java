package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Modelo de petición para registrar un nuevo producto")
public class ProductRequest {

    @Schema(description = "Descripción o nombre del producto", example = "Monitor Dell 24 pulgadas")
    private String description;

    @Schema(description = "Precio unitario del producto", example = "180.00")
    private Double price;
}
