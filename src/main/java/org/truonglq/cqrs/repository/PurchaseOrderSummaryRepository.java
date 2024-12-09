package org.truonglq.cqrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.truonglq.cqrs.entity.PurchaseOrderSummary;

import java.util.Optional;

public interface PurchaseOrderSummaryRepository extends JpaRepository<PurchaseOrderSummary, String> {
    Optional<PurchaseOrderSummary> findByState(String state);
}
