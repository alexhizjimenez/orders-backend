package com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.product;

import com.alexhiz.hexagonal.orders.application.port.out.ProductRepositoryPort;
import com.alexhiz.hexagonal.orders.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final ProductPersistenceMapper mapper;
    private final ProductRepository  repository;


    @Override
    public Product save(Product product) {
        ProductEntity toEntity = mapper.toEntity(product);
        ProductEntity savedEntity = repository.save(toEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        return repository.findById(productId).map(mapper::toDomain);
    }
}
