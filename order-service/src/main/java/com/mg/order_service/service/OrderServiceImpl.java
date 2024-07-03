package com.mg.order_service.service;

import com.mg.order_service.document.Order;
import com.mg.order_service.document.OrderItem;
import com.mg.order_service.enuns.OrderStatus;
import com.mg.order_service.respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderEventsService orderEventsService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderEventsService orderEventsService) {
        this.orderRepository = orderRepository;
        this.orderEventsService = orderEventsService;
    }

    @Override
    public Mono<Order> createOrder(String customerId, List<OrderItem> items) {
        Order order = Order.builder()
                .customerId(customerId)
                .items(items)
                .status(OrderStatus.PENDING)
                .orderDate(LocalDateTime.now())
                .build();
        return orderRepository.save(order)
                .doOnSuccess(savedOrder -> orderEventsService.publish(order));
    }

    @Override
    public Mono<Order> cancelOrder(String orderId) {
        return orderRepository.findById(orderId)
                .flatMap(order -> {
                    order.setStatus(OrderStatus.CANCELLED);
                    return orderRepository.save(order);
                });
    }

    @Override
    public Mono<Order> getOrderById(String orderId) {
        return orderRepository.findById(orderId);
    }
}
