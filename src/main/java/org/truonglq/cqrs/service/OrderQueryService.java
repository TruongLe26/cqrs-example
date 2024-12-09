package org.truonglq.cqrs.service;

import org.truonglq.cqrs.dto.PurchaseOrderSummaryDto;

import java.util.List;

public interface OrderQueryService {
    List<PurchaseOrderSummaryDto> getSalesSummaryGroupByState();
    PurchaseOrderSummaryDto getSalesSummaryByState(String state);
    double getTotalSale();
}
