package com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.branch;

import com.alexhiz.hexagonal.orders.application.port.out.BranchRepositoryPort;
import com.alexhiz.hexagonal.orders.domain.model.Branch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BranchPersistenceAdapter implements BranchRepositoryPort {

    private final BranchRepository branchRepository;
    private final BranchPersistenceMapper branchPersistenceMapper;

    @Override
    public Branch save(Branch branch) {
        BranchEntity branchEntity = branchPersistenceMapper.toEntity(branch);
        BranchEntity savedEntity = branchRepository.save(branchEntity);
        return branchPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Branch> findById(UUID branchId) {
        return branchRepository.findById(branchId).map(branchPersistenceMapper::toDomain);
    }
}
