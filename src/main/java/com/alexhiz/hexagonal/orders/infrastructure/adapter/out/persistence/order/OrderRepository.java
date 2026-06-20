package com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
