package com.mg.billing_service.events;

import lombok.Data;

@Data
public class InventoryConfirmationEvent {
    private String orderId;
    private Boolean isConfirmed;
}
