package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Schema(description = "Modelo de petición para registrar un nuevo pedido (orden)")
public class OrderRequest {

    @NotNull(message = "Branch ID is required")
    @Schema(description = "UUID de la sucursal donde se realiza el pedido", example = "9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d")
    private UUID branchId;

    @NotEmpty(message = "Order must contain at least one item")
    @Valid
    @Schema(description = "Lista de productos y sus cantidades que componen el pedido")
    private List<OrderItemRequest> items;
}
