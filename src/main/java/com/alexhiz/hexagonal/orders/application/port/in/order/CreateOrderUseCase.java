package com.alexhiz.hexagonal.orders.application.port.in.order;

import com.alexhiz.hexagonal.orders.domain.model.Order;

public interface CreateOrderUseCase {
    Order createOrder(Order order);
}
