package com.mg.inventory_service.events;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class Event<T> {
    private String id;
    private Long date;
    private EventType type;
    private T data;
}
