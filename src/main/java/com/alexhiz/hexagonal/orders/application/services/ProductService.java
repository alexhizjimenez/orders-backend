package com.alexhiz.hexagonal.orders.application.services;

import com.alexhiz.hexagonal.orders.application.port.in.product.CreateProductUseCase;
import com.alexhiz.hexagonal.orders.application.port.in.product.GetProductUseCase;
import com.alexhiz.hexagonal.orders.application.port.out.ProductRepositoryPort;
import com.alexhiz.hexagonal.orders.domain.exception.ProductNotFoundException;
import com.alexhiz.hexagonal.orders.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
public class ProductService implements CreateProductUseCase, GetProductUseCase {
    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Product createProduct(Product product) {
        return  productRepositoryPort.save(product);
    }

    @Override
    public Product getById(UUID productId) {
        return productRepositoryPort.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product not found: " + productId));
    }
}
