package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.response;

import com.alexhiz.hexagonal.orders.domain.model.Branch;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.request.BranchRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class BranchResponse {
    private UUID id;
    private String name;

    public static BranchResponse from(Branch branch) {
        return BranchResponse.builder()
                .id(branch.getId())
                .name(branch.getName())
                .build();
    }
}
