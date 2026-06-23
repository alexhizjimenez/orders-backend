package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest;

import com.alexhiz.hexagonal.orders.application.port.in.product.CreateProductUseCase;
import com.alexhiz.hexagonal.orders.application.port.in.product.GetProductUseCase;
import com.alexhiz.hexagonal.orders.domain.model.Product;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.request.ProductRequest;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.response.ProductResponse;
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
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Endpoints para la gestión de productos")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;

    @PostMapping
    @Operation(summary = "Crear un nuevo producto", description = "Registra un nuevo producto en el catálogo de la aplicación")
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente", content = @Content(schema = @Schema(implementation = ProductResponse.class)))
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = Product.builder().description(productRequest.description()).price(productRequest.price()).build();
        Product create = createProductUseCase.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponse.from(create));
    }
}
