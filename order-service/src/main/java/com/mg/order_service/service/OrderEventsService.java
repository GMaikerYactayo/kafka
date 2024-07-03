package com.mg.order_service.service;

import com.mg.order_service.document.Order;
import com.mg.order_service.events.Event;
import com.mg.order_service.events.EventType;
import com.mg.order_service.events.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class OrderEventsService {

    @Autowired
    private KafkaTemplate<String, Event<?>> producer;

    public void publish(Order order) {
        OrderCreatedEvent created = new OrderCreatedEvent();
        created.setData(order);
        created.setId(UUID.randomUUID().toString());
        created.setType(EventType.CREATED);
        created.setDate(new Date());

        this.producer.send("order_topic", created);
    }

}
