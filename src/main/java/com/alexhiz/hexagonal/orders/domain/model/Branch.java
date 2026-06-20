package com.alexhiz.hexagonal.orders.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Branch {
    @EqualsAndHashCode.Include
    private UUID id;
    private String name;
}
