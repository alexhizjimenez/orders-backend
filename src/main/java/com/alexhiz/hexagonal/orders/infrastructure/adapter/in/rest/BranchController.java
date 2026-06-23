package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest;

import com.alexhiz.hexagonal.orders.application.port.in.branch.CreateBranchUseCase;
import com.alexhiz.hexagonal.orders.application.port.in.branch.GetBranchUseCase;
import com.alexhiz.hexagonal.orders.domain.model.Branch;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.request.BranchRequest;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.response.BranchResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/branches")
@Tag(name = "Sucursales", description = "Endpoints para la gestión de sucursales")
public class BranchController {

    private final CreateBranchUseCase createBranchUseCase;
    private final GetBranchUseCase getBranchUseCase;

    @PostMapping
    @Operation(summary = "Crear una nueva sucursal", description = "Registra una nueva sucursal en el sistema")
    @ApiResponse(responseCode = "201", description = "Sucursal creada exitosamente", content = @Content(schema = @Schema(implementation = BranchResponse.class)))
    public ResponseEntity<BranchResponse> createBranch(@Valid @RequestBody BranchRequest branchRequest) {
        Branch branch = Branch.builder().name(branchRequest.getName()).build();
        Branch create = createBranchUseCase.createBranch(branch);
        return ResponseEntity.status(HttpStatus.CREATED).body(BranchResponse.from(create));
    }
}
