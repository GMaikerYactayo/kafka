package com.mg.billing_service.repository;

import com.mg.billing_service.document.Bill;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends ReactiveMongoRepository<Bill, String> {
}
