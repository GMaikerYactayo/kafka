package com.mg.billing_service.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bill")
public class Bill {
    @Id
    private String id;
    private String orderId;
    private double totalAmount;
    private LocalDateTime billingTime;
}
