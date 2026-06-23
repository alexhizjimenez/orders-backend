package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Modelo de petición para registrar una nueva sucursal")
public class BranchRequest {

    @Schema(description = "Nombre único de la sucursal", example = "Sucursal Oeste")
    private String name;
}
