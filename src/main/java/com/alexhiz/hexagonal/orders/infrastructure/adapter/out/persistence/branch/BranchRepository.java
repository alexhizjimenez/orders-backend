package com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.branch;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BranchRepository extends JpaRepository<BranchEntity, UUID> {
}
