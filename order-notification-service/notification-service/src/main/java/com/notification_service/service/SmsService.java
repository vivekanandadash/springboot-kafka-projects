package com.notification_service.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SmsService {

    @Value("${twilio.sms-from}")
    private String smsFrom;

    public void sendOrderSms(
            String to,
            String customerName,
            Long orderId,
            String status,
            BigDecimal totalAmount
    ) {

        try {

            String smsBody;

            // =========================
            // SUCCESS SMS
            // =========================
            if (status.equalsIgnoreCase("SUCCESS")) {

                smsBody =
                        "ShopCore: Hi "
                                + customerName
                                + ", your order #"
                                + orderId
                                + " has been placed successfully."
                                + " Amount: ₹"
                                + totalAmount
                                + ". Thank you for shopping with us.";

            }

            // =========================
            // CANCEL SMS
            // =========================
            else {

                smsBody =
                        "ShopCore: Hi "
                                + customerName
                                + ", your order #"
                                + orderId
                                + " has been cancelled."
                                + " Refund will be processed if applicable.";
            }

            // Sending SMS using Twilio
            Message message = Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(smsFrom),
                    smsBody
            ).create();

            System.out.println(
                    "SMS Sent Successfully : "
                            + message.getSid()
            );

        } catch (Exception e) {

            System.out.println("SMS Sending Failed");

            e.printStackTrace();
        }
    }
}