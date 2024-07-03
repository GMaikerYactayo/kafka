package com.mg.billing_service.service;

import com.mg.billing_service.document.Bill;
import reactor.core.publisher.Mono;

public interface BillService {

    Mono<Bill> createBill(Bill bill);

}
