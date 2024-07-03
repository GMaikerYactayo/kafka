package com.mg.inventory_service.service;

import com.mg.inventory_service.documents.Inventory;
import com.mg.inventory_service.documents.Product;
import com.mg.inventory_service.events.InventoryConfirmationEvent;
import com.mg.inventory_service.entity.Order;
import com.mg.inventory_service.repository.InventoryRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final KafkaTemplate<String, InventoryConfirmationEvent> kafkaTemplate;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, KafkaTemplate<String, InventoryConfirmationEvent> kafkaTemplate) {
        this.inventoryRepository = inventoryRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Mono<Inventory> createProduct(Product product) {
        Inventory inventory = new Inventory();
        inventory.setAvailableQuantity(product.getQuantity());
        inventory.setProduct(product);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Mono<Inventory> findByProductName(String name) {
        return inventoryRepository.findByProductName(name);
    }

    @Override
    public void processOrder(Order order) {
        checkInventory(order)
                .flatMap(isAvailable -> {
                    if (isAvailable) {
                        return updateInventory(order).thenReturn(true);
                    } else {
                        return Mono.just(false);
                    }
                })
                .subscribe(isAvailable -> {
                    InventoryConfirmationEvent confirmation = new InventoryConfirmationEvent();
                    confirmation.setOrderId(order.getOrderId());
                    confirmation.setIsConfirmed(isAvailable);

                    kafkaTemplate.send("inventory-confirmation", confirmation);
                });
    }

    private Mono<Boolean> checkInventory(Order order) {
        return Flux.fromIterable(order.getItems())
                .flatMap(item -> inventoryRepository.findProductById(item.getProductId())
                        .map(inventoryItem -> inventoryItem != null && inventoryItem.getAvailableQuantity() >= item.getQuantity())
                        .defaultIfEmpty(false))
                .all(isAvailable -> isAvailable);
    }

    private Mono<Void> updateInventory(Order order) {
        return Flux.fromIterable(order.getItems())
                .flatMap(item -> inventoryRepository.findProductById(item.getProductId())
                        .flatMap(inventory -> {
                            if (inventory != null) {
                                inventory.setAvailableQuantity(inventory.getAvailableQuantity() - item.getQuantity());
                                return inventoryRepository.save(inventory);
                            } else {
                                return Mono.empty();
                            }
                        }))
                .then();
    }

}
