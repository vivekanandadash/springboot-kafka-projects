package com.notification_service.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOrderEmail(
            String to,
            String customerName,
            Long orderId,
            String status,
            String totalAmount
    ) {

        try {

            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true);

            helper.setTo(to);

            String subject;
            String body;

            // SUCCESS MAIL
            if (status.equalsIgnoreCase("SUCCESS")) {

                subject = "Order Placed Successfully";

                body =
                        "<h2 style='color:green;'>ShopCore</h2>"
                                + "<p>Hello " + customerName + ",</p>"
                                + "<p>Your order placed successfully.</p>"
                                + "<p><b>Order ID:</b> " + orderId + "</p>"
                                + "<p><b>Total Amount:</b> ₹" + totalAmount + "</p>"
                                + "<p>Thank you for shopping with us.</p>";
            }

            // CANCEL MAIL
            else {

                subject = "Order Cancelled";

                body =
                        "<h2 style='color:red;'>ShopCore</h2>"
                                + "<p>Hello " + customerName + ",</p>"
                                + "<p>Your order has been cancelled.</p>"
                                + "<p><b>Order ID:</b> " + orderId + "</p>"
                                + "<p>If payment deducted, refund will be processed soon.</p>";
            }

            helper.setSubject(subject);

            helper.setText(body, true);

            mailSender.send(message);

            System.out.println("Email Sent Successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}