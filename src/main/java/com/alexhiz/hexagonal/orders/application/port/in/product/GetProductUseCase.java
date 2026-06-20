package com.alexhiz.hexagonal.orders.application.port.in.product;

import com.alexhiz.hexagonal.orders.domain.model.Product;

import java.util.UUID;

public interface GetProductUseCase {
    Product getById(UUID productId);
}
