package com.notification_service.kafka;

import com.notification_service.dto.OrderEvent;
import com.notification_service.service.EmailService;
import com.notification_service.service.SmsService;
import com.notification_service.service.WhatsAppService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service // Marks this class as a Spring Service component
public class NotificationConsumer {

    // Email service object for sending emails
    private final EmailService emailService;

    // SMS service object for sending SMS
    private SmsService smsService;

    // WhatsApp service object for sending WhatsApp messages
    private WhatsAppService whatsAppService;

    // Constructor Injection
    // Spring automatically injects all required services here
    public NotificationConsumer(
            EmailService emailService,
            SmsService smsService,
            WhatsAppService whatsAppService
    ) {

        this.emailService = emailService;
        this.smsService = smsService;
        this.whatsAppService = whatsAppService;
    }

    // Kafka Listener
    // This method automatically listens to messages from Kafka topic "order-events"
    @KafkaListener(
            topics = "order-events",
            groupId = "notification-group"
    )
    public void consume(OrderEvent event) {

        // Print message when event is received from Kafka
        System.out.println("Order Event Received");

        // =========================
        // SEND EMAIL NOTIFICATION
        // =========================
        try {

            // Calling email service method
            emailService.sendOrderEmail(

                    // Customer email
                    event.getEmail(),

                    // Customer name
                    event.getCustomerName(),

                    // Order ID
                    event.getOrderId(),

                    // Order status
                    event.getStatus(),

                    // Total amount
                    // If amount is null then send "0"
                    event.getTotalAmount() != null
                            ? event.getTotalAmount().toString()
                            : "0"
            );

        } catch (Exception e) {

            // If email sending fails
            System.out.println("Email failed: " + e.getMessage());
        }

        // =========================
        // SEND SMS NOTIFICATION
        // =========================
        try {

            // Calling SMS service method
            smsService.sendOrderSms(

                    // Customer mobile number
                    event.getMobile(),

                    // Customer name
                    event.getCustomerName(),

                    // Order ID
                    event.getOrderId(),

                    // Order status
                    event.getStatus(),

                    // Total amount
                    event.getTotalAmount()
            );

        } catch (Exception e) {

            // If SMS sending fails
            System.out.println("SMS failed: " + e.getMessage());
        }

        // =========================
        // SEND WHATSAPP MESSAGE
        // =========================
        try {

            // Calling WhatsApp service method
            whatsAppService.sendWhatsappMessage(
                    event.getMobile(),
                    event.getCustomerName(),
                    event.getOrderId(),
                    event.getStatus(),
                    event.getTotalAmount()
            );

        } catch (Exception e) {

            // If WhatsApp sending fails
            System.out.println("WhatsApp failed: "
                    + e.getMessage());
        }
    }
}