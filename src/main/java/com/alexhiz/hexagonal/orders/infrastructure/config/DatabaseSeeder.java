package com.alexhiz.hexagonal.orders.infrastructure.config;

import com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.branch.BranchEntity;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.branch.BranchRepository;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.product.ProductEntity;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.out.persistence.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseSeeder implements ApplicationRunner {

    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (branchRepository.count() == 0) {
            log.info("------------------------------------------------------------------------");
            log.info("SEEDING INITIAL DATA FOR TESTING...");
            log.info("------------------------------------------------------------------------");

            BranchEntity b1 = branchRepository.save(BranchEntity.builder().name("Sucursal Norte").build());
            BranchEntity b2 = branchRepository.save(BranchEntity.builder().name("Sucursal Sur").build());

            log.info("Branches seeded successfully:");
            log.info("  Name: {} | ID: {}", b1.getName(), b1.getId());
            log.info("  Name: {} | ID: {}", b2.getName(), b2.getId());

            ProductEntity p1 = productRepository.save(ProductEntity.builder().description("Laptop Lenovo").price(1200.00).build());
            ProductEntity p2 = productRepository.save(ProductEntity.builder().description("Mouse Logitech").price(25.00).build());
            ProductEntity p3 = productRepository.save(ProductEntity.builder().description("Teclado Mecanico").price(75.00).build());

            log.info("Products seeded successfully:");
            log.info("  Name: {} | Price: ${} | ID: {}", p1.getDescription(), p1.getPrice(), p1.getId());
            log.info("  Name: {} | Price: ${} | ID: {}", p2.getDescription(), p2.getPrice(), p2.getId());
            log.info("  Name: {} | Price: ${} | ID: {}", p3.getDescription(), p3.getPrice(), p3.getId());
            log.info("------------------------------------------------------------------------");
        }
    }
}
