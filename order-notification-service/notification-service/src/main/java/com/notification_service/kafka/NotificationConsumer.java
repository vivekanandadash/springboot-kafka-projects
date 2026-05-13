package com.notification_service.kafka;

import com.notification_service.dto.OrderEvent;
import com.notification_service.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private final EmailService emailService;

    public NotificationConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(
            topics = "order-events",
            groupId = "notification-group"
    )
    public void consume(OrderEvent event) {

        System.out.println("Order Event Received");

        emailService.sendOrderEmail(
                event.getEmail(),
                event.getCustomerName(),
                event.getOrderId(),
                event.getStatus(),
                event.getTotalAmount().toString()
        );
    }
}
