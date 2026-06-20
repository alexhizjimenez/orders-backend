package com.alexhiz.hexagonal.orders.application.port.out;

import com.alexhiz.hexagonal.orders.domain.model.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepositoryPort {
    Order save(Order order);
    Optional<Order> findById(UUID orderId);
}
