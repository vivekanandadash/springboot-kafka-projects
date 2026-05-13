package com.notification_service.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
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

            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, true);

            helper.setTo(to);

            String subject = "";
            String htmlContent = "";

            // SUCCESS EMAIL
            if ("SUCCESS".equalsIgnoreCase(status)) {

                subject = "Your Order Has Been Placed Successfully";

                htmlContent =
                        "<div style='font-family: Arial; padding:20px;'>"

                                + "<h2 style='color:green;'>ShopCore Order Confirmation</h2>"

                                + "<p>Hello <b>" + customerName + "</b>,</p>"

                                + "<p>Your order has been placed successfully.</p>"

                                + "<table style='border-collapse: collapse; width: 400px;'>"

                                + "<tr>"
                                + "<td style='border:1px solid #ddd; padding:8px;'>Order ID</td>"
                                + "<td style='border:1px solid #ddd; padding:8px;'>"
                                + orderId +
                                "</td>"
                                + "</tr>"

                                + "<tr>"
                                + "<td style='border:1px solid #ddd; padding:8px;'>Status</td>"
                                + "<td style='border:1px solid #ddd; padding:8px;'>"
                                + status +
                                "</td>"
                                + "</tr>"

                                + "<tr>"
                                + "<td style='border:1px solid #ddd; padding:8px;'>Total Amount</td>"
                                + "<td style='border:1px solid #ddd; padding:8px;'>₹"
                                + totalAmount +
                                "</td>"
                                + "</tr>"

                                + "</table>"

                                + "<br>"

                                + "<p>Thank you for shopping with us ❤️</p>"

                                + "<p>Team ShopCore</p>"

                                + "</div>";
            }

            // CANCELLED EMAIL
            else if ("CANCELLED".equalsIgnoreCase(status)) {

                subject = "Your Order Has Been Cancelled";

                htmlContent =
                        "<div style='font-family: Arial; padding:20px;'>"

                                + "<h2 style='color:red;'>Order Cancelled</h2>"

                                + "<p>Hello <b>" + customerName + "</b>,</p>"

                                + "<p>We regret to inform you that your order has been cancelled.</p>"

                                + "<table style='border-collapse: collapse; width: 400px;'>"

                                + "<tr>"
                                + "<td style='border:1px solid #ddd; padding:8px;'>Order ID</td>"
                                + "<td style='border:1px solid #ddd; padding:8px;'>"
                                + orderId +
                                "</td>"
                                + "</tr>"

                                + "<tr>"
                                + "<td style='border:1px solid #ddd; padding:8px;'>Status</td>"
                                + "<td style='border:1px solid #ddd; padding:8px;'>"
                                + status +
                                "</td>"
                                + "</tr>"

                                + "</table>"

                                + "<br>"

                                + "<p>If payment was deducted, refund will be processed shortly.</p>"

                                + "<p>Need help? Contact support.</p>"

                                + "<p>Team ShopCore</p>"

                                + "</div>";
            }

            helper.setSubject(subject);

            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);

            System.out.println("Email Sent Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
