package com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.order;

import com.alexhiz.hexagonal.orders.application.port.out.OrderRepositoryPort;
import com.alexhiz.hexagonal.orders.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderRepositoryPort {

    private final OrderRepository orderRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderPersistenceMapper.toEntity(order);
        OrderEntity saved = orderRepository.save(orderEntity);
        return orderPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Order> findById(UUID orderId) {
        return orderRepository.findById(orderId).map(orderPersistenceMapper::toDomain);
    }
}
