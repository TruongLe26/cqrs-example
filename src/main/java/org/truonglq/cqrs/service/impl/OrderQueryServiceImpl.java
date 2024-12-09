package org.truonglq.cqrs.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.truonglq.cqrs.dto.PurchaseOrderSummaryDto;
import org.truonglq.cqrs.entity.PurchaseOrderSummary;
import org.truonglq.cqrs.repository.PurchaseOrderSummaryRepository;
import org.truonglq.cqrs.service.OrderQueryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService {

    private final PurchaseOrderSummaryRepository purchaseOrderSummaryRepository;

    public OrderQueryServiceImpl(PurchaseOrderSummaryRepository purchaseOrderSummaryRepository) {
        this.purchaseOrderSummaryRepository = purchaseOrderSummaryRepository;
    }

    @Override
    public List<PurchaseOrderSummaryDto> getSalesSummaryGroupByState() {
        return purchaseOrderSummaryRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseOrderSummaryDto getSalesSummaryByState(String state) {
        return purchaseOrderSummaryRepository.findByState(state)
                .map(this::entityToDto)
                .orElseGet(() -> new PurchaseOrderSummaryDto(state, 0.0));
    }

    @Override
    public double getTotalSale() {
        return purchaseOrderSummaryRepository.findAll()
                .stream()
                .mapToDouble(PurchaseOrderSummary::getTotalSale)
                .sum();
    }

    private PurchaseOrderSummaryDto entityToDto(PurchaseOrderSummary purchaseOrderSummary) {
        PurchaseOrderSummaryDto dto = new PurchaseOrderSummaryDto();
        dto.setState(purchaseOrderSummary.getState());
        dto.setTotalSale(purchaseOrderSummary.getTotalSale());
        return dto;
    }
}
