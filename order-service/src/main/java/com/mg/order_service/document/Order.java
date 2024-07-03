package com.mg.order_service.document;

import com.mg.order_service.enuns.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")
public class Order {
    @Id
    private String orderId;
    private String customerId;
    private List<OrderItem> items;
    private OrderStatus status;
    private LocalDateTime orderDate;
}
