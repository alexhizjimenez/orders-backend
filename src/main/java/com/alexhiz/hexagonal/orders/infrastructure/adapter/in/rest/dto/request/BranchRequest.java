package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de petición para registrar una nueva sucursal")
public record BranchRequest(
    @Schema(description = "Nombre único de la sucursal", example = "Sucursal Oeste")
    String name
) {}
