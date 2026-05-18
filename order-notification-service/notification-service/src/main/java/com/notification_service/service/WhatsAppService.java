package com.notification_service.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WhatsAppService {

    @Value("${twilio.whatsapp-from}")
    private String whatsappFrom;

    public void sendWhatsappMessage(
            String to,
            String customerName,
            Long orderId,
            String status,
            BigDecimal totalAmount
    ) {

        try {

            String whatsappBody;

            if (status.equalsIgnoreCase("SUCCESS")) {

                whatsappBody =
                        "✅ ShopCore Order Confirmed\n\n"
                                + "Hi " + customerName + ",\n"
                                + "Thank you for shopping with ShopCore.\n\n"
                                + "Your order has been placed successfully.\n"
                                + "Order ID: #" + orderId + "\n"
                                + "Total Amount: ₹" + totalAmount + "\n\n"
                                + "We’ll notify you once your order is packed and shipped.\n\n"
                                + "Team ShopCore";

            } else {

                whatsappBody =
                        "❌ ShopCore Order Cancelled\n\n"
                                + "Hi " + customerName + ",\n"
                                + "Your order has been cancelled successfully.\n\n"
                                + "Order ID: #" + orderId + "\n\n"
                                + "Any paid amount, if applicable, will be refunded as per our refund policy.\n\n"
                                + "Team ShopCore";
            }

            Message message = Message.creator(
                    new PhoneNumber("whatsapp:" + to),
                    new PhoneNumber(whatsappFrom),
                    whatsappBody
            ).create();

            System.out.println(
                    "WhatsApp Message Sent Successfully : "
                            + message.getSid()
            );

        } catch (Exception e) {

            System.out.println("WhatsApp Message Sending Failed");

            e.printStackTrace();
        }
    }
}