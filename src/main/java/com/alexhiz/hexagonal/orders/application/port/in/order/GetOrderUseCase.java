package com.alexhiz.hexagonal.orders.application.port.in.order;

import com.alexhiz.hexagonal.orders.domain.model.Order;

import java.util.UUID;

public interface GetOrderUseCase {
    Order getById(UUID orderId);
}
