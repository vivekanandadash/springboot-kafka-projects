package com.notification_service.kafka;

import com.notification_service.dto.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(
            topics = "order-events",
            groupId = "notification-group"
    )
    public void consume(OrderEvent event) {

        System.out.println("Event Received");
        System.out.println("Email: " + event.getEmail());
        System.out.println("Order ID: " + event.getOrderId());
        System.out.println("Status: " + event.getStatus());
    }
}
