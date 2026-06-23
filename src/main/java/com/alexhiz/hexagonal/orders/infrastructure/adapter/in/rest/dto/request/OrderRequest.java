package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Schema(description = "Modelo de petición para registrar un nuevo pedido (orden)")
public record OrderRequest(
    @NotNull(message = "Branch ID is required")
    @Schema(description = "UUID de la sucursal donde se realiza el pedido", example = "9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d")
    UUID branchId,

    @NotEmpty(message = "Order must contain at least one item")
    @Valid
    @Schema(description = "Lista de productos y sus cantidades que componen el pedido")
    List<OrderItemRequest> items
) {}
