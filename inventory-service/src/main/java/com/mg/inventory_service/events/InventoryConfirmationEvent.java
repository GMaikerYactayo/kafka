package com.mg.inventory_service.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryConfirmationEvent {
    private String orderId;
    private Boolean isConfirmed;
}
