package com.alexhiz.hexagonal.orders.application.port.out;

import com.alexhiz.hexagonal.orders.domain.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(UUID productId);
}
