package com.mg.inventory_service.events;

import com.mg.inventory_service.entity.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCreatedEvent extends Event<Order> {
}
