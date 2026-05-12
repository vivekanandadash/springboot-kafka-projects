package com.order_service_example.kafka;

import com.order_service_example.dto.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    private static final String TOPIC = "order-events";

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println("Event Sent : " + event);
    }
}