package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.response;

import com.alexhiz.hexagonal.orders.domain.model.Branch;
import lombok.Builder;

import java.util.UUID;

@Builder
public record BranchResponse(
    UUID id,
    String name
) {
    public static BranchResponse from(Branch branch) {
        return BranchResponse.builder()
                .id(branch.getId())
                .name(branch.getName())
                .build();
    }
}
