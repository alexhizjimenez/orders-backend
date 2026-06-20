package com.alexhiz.hexagonal.orders.infrastructure.config;

import com.alexhiz.hexagonal.orders.application.port.out.BranchRepositoryPort;
import com.alexhiz.hexagonal.orders.application.port.out.OrderRepositoryPort;
import com.alexhiz.hexagonal.orders.application.port.out.ProductRepositoryPort;
import com.alexhiz.hexagonal.orders.application.services.BranchService;
import com.alexhiz.hexagonal.orders.application.services.OrderService;
import com.alexhiz.hexagonal.orders.application.services.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public BranchService branchService(BranchRepositoryPort  branchRepositoryPort) {
        return new BranchService(branchRepositoryPort);
    }

    @Bean
    public ProductService productService(ProductRepositoryPort productRepositoryPort) {
        return new ProductService(productRepositoryPort);
    }

    @Bean
    public OrderService orderService(OrderRepositoryPort orderRepositoryPort, BranchService branchService, ProductService productService) {
        return new OrderService(orderRepositoryPort, branchService, productService);
    }
}
