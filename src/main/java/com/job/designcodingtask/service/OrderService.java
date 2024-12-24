package com.job.designcodingtask.service;

import com.job.designcodingtask.model.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final KafkaTemplate<String, OrderRequest> kafkaTemplate;

    public void processOrder(OrderRequest orderRequest) {
        kafkaTemplate.send("order-topic", orderRequest);
        System.out.println("Order processed: " + orderRequest);
    }
}
