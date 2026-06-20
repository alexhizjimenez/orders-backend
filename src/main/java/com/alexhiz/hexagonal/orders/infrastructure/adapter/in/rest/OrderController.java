package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest;

import com.alexhiz.hexagonal.orders.application.port.in.order.CreateOrderUseCase;
import com.alexhiz.hexagonal.orders.application.port.in.order.GetOrderUseCase;
import com.alexhiz.hexagonal.orders.domain.model.Order;
import com.alexhiz.hexagonal.orders.domain.model.OrderItem;
import com.alexhiz.hexagonal.orders.domain.model.Product;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.request.OrderRequest;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.response.OrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        List<OrderItem> domainItems = orderRequest.getItems().stream()
                .map(itemReq -> OrderItem.builder()
                        .product(Product.builder().id(itemReq.getProductId()).build())
                        .quantity(itemReq.getQuantity())
                        .build())
                .collect(Collectors.toList());

        Order order = Order.builder()
                .branchId(orderRequest.getBranchId())
                .items(domainItems)
                .build();

        Order createdOrder = createOrderUseCase.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderResponse.from(createdOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable UUID id) {
        Order order = getOrderUseCase.getById(id);
        return ResponseEntity.ok(OrderResponse.from(order));
    }
}
