package com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.order;

import com.alexhiz.hexagonal.orders.domain.model.Order;
import com.alexhiz.hexagonal.orders.domain.model.OrderItem;
import com.alexhiz.hexagonal.orders.domain.model.Product;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.branch.BranchEntity;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.product.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderPersistenceMapper {

    public OrderEntity toEntity(Order order) {
        if (order == null) {
            return null;
        }

        OrderEntity orderEntity = OrderEntity.builder()
                .id(order.getId())
                .branch(BranchEntity.builder().id(order.getBranchId()).build())
                .orderDate(order.getOrderDate())
                .total(order.getTotal())
                .items(new ArrayList<>())
                .build();

        if (order.getItems() != null) {
            List<OrderItemEntity> itemEntities = order.getItems().stream()
                    .map(item -> toItemEntity(item, orderEntity))
                    .collect(Collectors.toList());
            orderEntity.setItems(itemEntities);
        }

        return orderEntity;
    }

    private OrderItemEntity toItemEntity(OrderItem item, OrderEntity orderEntity) {
        if (item == null) {
            return null;
        }
        return OrderItemEntity.builder()
                .order(orderEntity)
                .product(item.getProduct() != null ? ProductEntity.builder()
                        .id(item.getProduct().getId())
                        .description(item.getProduct().getDescription())
                        .price(item.getProduct().getPrice())
                        .build() : null)
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .build();
    }

    public Order toDomain(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }

        Order order = Order.builder()
                .id(orderEntity.getId())
                .branchId(orderEntity.getBranch() != null ? orderEntity.getBranch().getId() : null)
                .orderDate(orderEntity.getOrderDate())
                .total(orderEntity.getTotal())
                .items(new ArrayList<>())
                .build();

        if (orderEntity.getItems() != null) {
            List<OrderItem> items = orderEntity.getItems().stream()
                    .map(this::toItemDomain)
                    .collect(Collectors.toList());
            order.setItems(items);
        }

        return order;
    }

    private OrderItem toItemDomain(OrderItemEntity itemEntity) {
        if (itemEntity == null) {
            return null;
        }
        return OrderItem.builder()
                .product(itemEntity.getProduct() != null ? Product.builder()
                        .id(itemEntity.getProduct().getId())
                        .description(itemEntity.getProduct().getDescription())
                        .price(itemEntity.getProduct().getPrice())
                        .build() : null)
                .quantity(itemEntity.getQuantity())
                .unitPrice(itemEntity.getUnitPrice())
                .build();
    }
}
