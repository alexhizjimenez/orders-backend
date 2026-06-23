package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Modelo de petición para un ítem dentro de un pedido")
public record OrderItemRequest(
    @NotNull(message = "Product ID is required")
    @Schema(description = "UUID del producto a pedir", example = "a5e9dc32-2d88-4db8-9e5b-1b2c3d4e5f6g")
    UUID productId,

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Schema(description = "Cantidad solicitada del producto", example = "2")
    Integer quantity
) {}
