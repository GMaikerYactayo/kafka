package com.mg.inventory_service.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private String orderId;
    private String customerId;
    private List<OrderItem> items;
    private String status;
    private LocalDateTime orderDate;
}
