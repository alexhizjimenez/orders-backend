package com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.branch;

import com.alexhiz.hexagonal.orders.domain.model.Branch;
import org.springframework.stereotype.Component;

@Component
public class BranchPersistenceMapper {

    BranchEntity toEntity(Branch branch) {
        return BranchEntity.builder()
                .id(branch.getId())
                .name(branch.getName())
                .build();
    }

    Branch toDomain(BranchEntity branchEntity) {
        return  Branch.builder()
                .id(branchEntity.getId())
                .name(branchEntity.getName())
                .build();
    }
}
