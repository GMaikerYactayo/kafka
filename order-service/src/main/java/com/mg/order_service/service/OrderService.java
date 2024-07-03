package com.mg.order_service.service;

import com.mg.order_service.document.Order;
import com.mg.order_service.document.OrderItem;
import reactor.core.publisher.Mono;

import java.util.List;

public interface OrderService {
    Mono<Order> createOrder(String customerId, List<OrderItem> items);
    Mono<Order> cancelOrder(String orderId);
    Mono<Order> getOrderById(String orderId);
}
