package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de petición para registrar un nuevo producto")
public record ProductRequest(
    @Schema(description = "Descripción o nombre del producto", example = "Monitor Dell 24 pulgadas")
    String description,

    @Schema(description = "Precio unitario del producto", example = "180.00")
    Double price
) {}
