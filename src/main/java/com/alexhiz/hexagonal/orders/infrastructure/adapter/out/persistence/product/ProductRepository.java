package com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.product;

import com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.branch.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}
