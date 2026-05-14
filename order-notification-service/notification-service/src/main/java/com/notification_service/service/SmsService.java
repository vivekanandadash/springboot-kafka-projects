package com.notification_service.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioMessageService {

    @Value("${twilio.sms-from}")
    private String smsFrom;

    @Value("${twilio.whatsapp-from}")
    private String whatsappFrom;

    public String sendSms(String to, String messageBody) {

        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(smsFrom),
                messageBody
        ).create();

        return message.getSid();
    }

    public String sendWhatsappMessage(String to, String messageBody) {

        Message message = Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber(whatsappFrom),
                messageBody
        ).create();

        return message.getSid();
    }
}
