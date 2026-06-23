package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest;

import com.alexhiz.hexagonal.orders.application.port.in.order.CreateOrderUseCase;
import com.alexhiz.hexagonal.orders.application.port.in.order.GetOrderUseCase;
import com.alexhiz.hexagonal.orders.domain.model.Order;
import com.alexhiz.hexagonal.orders.domain.model.OrderItem;
import com.alexhiz.hexagonal.orders.domain.model.Product;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.request.OrderRequest;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.response.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Pedidos", description = "Endpoints para la gestión de pedidos (órdenes)")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;

    @PostMapping
    @Operation(summary = "Crear un nuevo pedido", description = "Registra un nuevo pedido asociándolo a una sucursal y especificando la lista de productos y cantidades")
    @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente", content = @Content(schema = @Schema(implementation = OrderResponse.class)))
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        List<OrderItem> domainItems = orderRequest.items().stream()
                .map(itemReq -> OrderItem.builder()
                        .product(Product.builder().id(itemReq.productId()).build())
                        .quantity(itemReq.quantity())
                        .build())
                .collect(Collectors.toList());

        Order order = Order.builder()
                .branchId(orderRequest.branchId())
                .items(domainItems)
                .build();

        Order createdOrder = createOrderUseCase.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderResponse.from(createdOrder));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un pedido por ID", description = "Recupera la información detallada de un pedido específico usando su identificador único (UUID)")
    @ApiResponse(responseCode = "200", description = "Pedido encontrado", content = @Content(schema = @Schema(implementation = OrderResponse.class)))
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable UUID id) {
        Order order = getOrderUseCase.getById(id);
        return ResponseEntity.ok(OrderResponse.from(order));
    }
}
