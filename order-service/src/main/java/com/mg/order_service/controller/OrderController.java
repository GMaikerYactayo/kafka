package com.mg.order_service.controller;

import com.mg.order_service.document.Order;
import com.mg.order_service.document.OrderItem;
import com.mg.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Mono<ResponseEntity<Order>> createOrder(@RequestBody List<OrderItem> items, ServerWebExchange exchange) {
        String customerId = UUID.randomUUID().toString();
        return orderService.createOrder(customerId, items)
                .map(ResponseEntity.status(HttpStatus.CREATED)::body);
    }

    @PutMapping("/cancel/{orderId}")
    public Mono<ResponseEntity<Order>> cancelOrder(@PathVariable String orderId, ServerWebExchange exchange) {
        return orderService.cancelOrder(orderId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{orderId}")
    public Mono<ResponseEntity<Order>> getOrderById(@PathVariable String orderId, ServerWebExchange exchange) {
        return orderService.getOrderById(orderId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
