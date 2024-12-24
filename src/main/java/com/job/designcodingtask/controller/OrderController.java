package com.job.designcodingtask.controller;

import com.job.designcodingtask.model.OrderRequest;
import com.job.designcodingtask.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public String receiveOrder(@RequestBody OrderRequest orderRequest) {
        orderService.processOrder(orderRequest);
        return "Order received and processed.";
    }
}
