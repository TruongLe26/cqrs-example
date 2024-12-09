package org.truonglq.cqrs.controller.query;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.truonglq.cqrs.dto.PurchaseOrderSummaryDto;
import org.truonglq.cqrs.service.OrderQueryService;

import java.util.List;

@RestController
@RequestMapping("po")
//@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "false")
public class OrderQueryController {

    private final OrderQueryService orderQueryService;

    public OrderQueryController(OrderQueryService orderQueryService) {
        this.orderQueryService = orderQueryService;
    }

    @GetMapping("/summary")
    @ResponseStatus(HttpStatus.OK)
    List<PurchaseOrderSummaryDto> getSummary() {
        return orderQueryService.getSalesSummaryGroupByState();
    }

    @GetMapping("/summary/{state}")
    @ResponseStatus(HttpStatus.OK)
    PurchaseOrderSummaryDto getStateSummary(@PathVariable String state) {
        return orderQueryService.getSalesSummaryByState(state);
    }

    @GetMapping("/total-sale")
    @ResponseStatus(HttpStatus.OK)
    Double getTotalSale() {
        return orderQueryService.getTotalSale();
    }
}
