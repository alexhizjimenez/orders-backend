package com.alexhiz.hexagonal.orders.application.port.out;

import com.alexhiz.hexagonal.orders.domain.model.Branch;

import java.util.Optional;
import java.util.UUID;

public interface BranchRepositoryPort {
    Branch save(Branch branch);
    Optional<Branch> findById(UUID branchId);
}
