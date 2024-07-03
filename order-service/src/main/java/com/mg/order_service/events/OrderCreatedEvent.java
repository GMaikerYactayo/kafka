package com.mg.order_service.events;

import com.mg.order_service.document.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCreatedEvent extends Event<Order> {
}
