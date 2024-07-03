package com.mg.inventory_service.repository;

import com.mg.inventory_service.documents.Inventory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface InventoryRepository extends ReactiveMongoRepository<Inventory, String> {

    Mono<Inventory> findProductById(String productId);
    Mono<Inventory> findByProductName(String name);

}
