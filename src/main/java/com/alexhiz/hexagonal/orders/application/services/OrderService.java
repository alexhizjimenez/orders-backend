package com.alexhiz.hexagonal.orders.application.services;

import com.alexhiz.hexagonal.orders.application.port.in.branch.GetBranchUseCase;
import com.alexhiz.hexagonal.orders.application.port.in.order.CreateOrderUseCase;
import com.alexhiz.hexagonal.orders.application.port.in.order.GetOrderUseCase;
import com.alexhiz.hexagonal.orders.application.port.in.product.GetProductUseCase;
import com.alexhiz.hexagonal.orders.application.port.out.OrderRepositoryPort;
import com.alexhiz.hexagonal.orders.domain.exception.OrderNotFoundException;
import com.alexhiz.hexagonal.orders.domain.model.Branch;
import com.alexhiz.hexagonal.orders.domain.model.Order;
import com.alexhiz.hexagonal.orders.domain.model.OrderItem;
import com.alexhiz.hexagonal.orders.domain.model.Product;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class OrderService implements CreateOrderUseCase, GetOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final GetBranchUseCase getBranchUseCase;
    private final GetProductUseCase getProductUseCase;

    @Override
    public Order createOrder(Order order) {
        // Validate that the branch exists
        Branch branch = getBranchUseCase.getById(order.getBranchId());

        // Process items: fetch product, update prices, compute total
        double total = 0.0;
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                if (item.getProduct() == null || item.getProduct().getId() == null) {
                    throw new IllegalArgumentException("Product and Product ID must not be null");
                }
                Product product = getProductUseCase.getById(item.getProduct().getId());
                item.setProduct(product);
                item.setUnitPrice(product.getPrice());
                total += item.getQuantity() * product.getPrice();
            }
        }

        order.setTotal(total);
        order.setOrderDate(LocalDateTime.now());

        return orderRepositoryPort.save(order);
    }

    @Override
    public Order getById(UUID orderId) {
        return orderRepositoryPort.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + orderId));
    }
}
