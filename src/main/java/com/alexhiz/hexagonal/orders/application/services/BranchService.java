package com.alexhiz.hexagonal.orders.application.services;

import com.alexhiz.hexagonal.orders.application.port.in.branch.CreateBranchUseCase;
import com.alexhiz.hexagonal.orders.application.port.in.branch.GetBranchUseCase;
import com.alexhiz.hexagonal.orders.application.port.out.BranchRepositoryPort;
import com.alexhiz.hexagonal.orders.domain.exception.BranchNotFoundException;
import com.alexhiz.hexagonal.orders.domain.model.Branch;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class BranchService implements CreateBranchUseCase, GetBranchUseCase {

    private final BranchRepositoryPort branchRepositoryPort;

    @Override
    public Branch createBranch(Branch branch) {
        return branchRepositoryPort.save(branch);
    }

    @Override
    public Branch getById(UUID branchId) {
        return branchRepositoryPort.findById(branchId).orElseThrow(()-> new BranchNotFoundException("Branch not found: " + branchId));
    }
}
