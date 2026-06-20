package com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.product;

import com.alexhiz.hexagonal.orders.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceMapper {
    ProductEntity toEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice()).build();
    }

    Product toDomain(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .build();
    }
}
