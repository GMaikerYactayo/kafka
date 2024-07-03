package com.mg.inventory_service.service;

import com.mg.inventory_service.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventsService {

    @Autowired
    private InventoryService inventoryService;

    @KafkaListener(
            topics = "order_topic",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "inventory-group")
    public void consumer(OrderCreatedEvent event) {
        log.info("Received Order created event with Id={}, data={}",
                event.getId(),
                event.getData());
        inventoryService.processOrder(event.getData());
    }

}
