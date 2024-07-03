package com.mg.inventory_service.controller;

import com.mg.inventory_service.documents.Inventory;
import com.mg.inventory_service.documents.Product;
import com.mg.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;


    @PostMapping("/createProduct")
    public Mono<ResponseEntity<Inventory>> createOrder(@RequestBody Product product, ServerWebExchange exchange) {
        return inventoryService.createProduct(product)
                .map(ResponseEntity.status(HttpStatus.CREATED)::body);
    }

    @GetMapping("/product/{name}")
    public Mono<ResponseEntity<Inventory>> getProductByName(@PathVariable String name, ServerWebExchange exchange) {
        return inventoryService.findByProductName(name)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
