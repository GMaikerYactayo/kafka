package com.mg.inventory_service.entity;

import lombok.Data;

@Data
public class OrderItem {
    private String productId;
    private int quantity;
    private double price;
}
