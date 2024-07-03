package com.mg.inventory_service.service;

import com.mg.inventory_service.documents.Inventory;
import com.mg.inventory_service.documents.Product;
import com.mg.inventory_service.entity.Order;
import reactor.core.publisher.Mono;

public interface InventoryService {
    void processOrder(Order order);
    Mono<Inventory> createProduct(Product product);
    Mono<Inventory> findByProductName(String productId);
}
