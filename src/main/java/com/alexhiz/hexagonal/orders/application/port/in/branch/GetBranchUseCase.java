package com.alexhiz.hexagonal.orders.application.port.in.branch;

import com.alexhiz.hexagonal.orders.domain.model.Branch;

import java.util.UUID;

public interface GetBranchUseCase {
    Branch getById(UUID branchId);
}
