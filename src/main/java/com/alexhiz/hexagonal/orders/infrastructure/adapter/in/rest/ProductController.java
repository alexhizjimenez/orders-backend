package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest;

import com.alexhiz.hexagonal.orders.application.port.in.product.CreateProductUseCase;
import com.alexhiz.hexagonal.orders.application.port.in.product.GetProductUseCase;
import com.alexhiz.hexagonal.orders.domain.model.Product;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.request.ProductRequest;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.response.ProductResponse;
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
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = Product.builder().description(productRequest.getDescription()).price(productRequest.getPrice()).build();
        Product create = createProductUseCase.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponse.from(create));
    }
}
