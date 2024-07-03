package com.mg.billing_service.service;

import com.mg.billing_service.document.Bill;
import com.mg.billing_service.events.InventoryConfirmationEvent;
import com.mg.billing_service.repository.BillRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final KafkaTemplate<String, Bill> kafkaTemplate;

    public BillServiceImpl(BillRepository billRepository, KafkaTemplate<String, Bill> kafkaTemplate) {
        this.billRepository = billRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "inventory-confirmation",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "billing-group")
    public void consume(InventoryConfirmationEvent confirmation) {
        System.out.println(confirmation);
        if (confirmation.getIsConfirmed().equals(true)) {
            Bill bill = new Bill();
            bill.setOrderId(confirmation.getOrderId());
            bill.setTotalAmount(10.00);
            bill.setBillingTime(LocalDateTime.now());
            createBill(bill).subscribe();
        } else {
            System.out.println("No se puede crear fatura porque ");
        }
    }

    @Override
    public Mono<Bill> createBill(Bill bill) {
        return billRepository.save(bill).doOnSuccess(billSaved -> kafkaTemplate.send("billing", bill));
    }
}
