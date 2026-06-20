package com.alexhiz.hexagonal.orders.application.port.in.product;

import com.alexhiz.hexagonal.orders.domain.model.Product;

public interface CreateProductUseCase {
    Product createProduct(Product product);
}
