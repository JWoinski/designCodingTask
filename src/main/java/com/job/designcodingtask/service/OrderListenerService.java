package com.job.designcodingtask.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.job.designcodingtask.model.dto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderListenerService implements MessageListener<String, OrderRequestDto> {
    private final OrderService orderService;
    @Override
    @KafkaListener(topics = "order-topic", groupId = "test-consumer-group")
    public void onMessage(ConsumerRecord<String, OrderRequestDto> record) {
        OrderRequestDto orderRequest = null;
        System.out.println("dupa123");
        try {
            // Tak wiem, partyzantka ;) Też nie wiem co się wysypało w konfiguracji, że inaczej nie idzie tego przerobić
            String recordString = record.toString();
            String jsonValue = recordString.replaceAll(".*value = (\\{.*\\}).*", "$1");
            ObjectMapper mapper = new ObjectMapper();
            orderRequest = mapper.readValue(jsonValue, OrderRequestDto.class);
            log.info("Received order request: " + orderRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (orderRequest != null) {
            orderService.saveOrder(orderRequest);
            log.info("Order saved in db.");
        }
        orderService.sendEmailAsync(orderRequest.getReceiverEmail(), "Order Confirmation", "Your order has been received.");
        log.info("Order confirmation sent.");
    }
}
