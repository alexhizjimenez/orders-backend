package com.alexhiz.hexagonal.orders.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {
    @EqualsAndHashCode.Include
    private UUID id;
    @EqualsAndHashCode.Include
    private UUID branchId;
    private LocalDateTime orderDate;
    private Double total;
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();
}
