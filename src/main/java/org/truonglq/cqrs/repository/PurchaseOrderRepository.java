package org.truonglq.cqrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.truonglq.cqrs.entity.PurchaseOrder;

import java.util.Optional;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    Optional<PurchaseOrder> findById(long orderId);
}
