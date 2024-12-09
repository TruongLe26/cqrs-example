package org.truonglq.cqrs.controller.command;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.truonglq.cqrs.dto.OrderCommandDto;
import org.truonglq.cqrs.service.OrderCommandService;

@RestController
@RequestMapping("po")
//@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "true")
public class OrderCommandController {

    private final OrderCommandService orderCommandService;

    public OrderCommandController(OrderCommandService orderCommandService) {
        this.orderCommandService = orderCommandService;
    }

    @PostMapping("/sale")
    @ResponseStatus(HttpStatus.OK)
    void placeOrder(@RequestBody OrderCommandDto orderCommandDto) {
        orderCommandService.createOrder(orderCommandDto.getUserIndex(), orderCommandDto.getProductIndex());
    }

    @PutMapping("/cancel-order/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    void cancelOrder(@PathVariable long orderId) {
        orderCommandService.cancelOrder(orderId);
    }

}
