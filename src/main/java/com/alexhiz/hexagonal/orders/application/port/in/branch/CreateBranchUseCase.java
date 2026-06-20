package com.alexhiz.hexagonal.orders.application.port.in.branch;

import com.alexhiz.hexagonal.orders.domain.model.Branch;

public interface CreateBranchUseCase {
    Branch createBranch(Branch branch);
}
