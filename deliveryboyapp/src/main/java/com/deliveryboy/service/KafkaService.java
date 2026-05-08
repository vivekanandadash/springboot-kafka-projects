package com.deliveryboy.service;

import com.deliveryboy.config.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
public class KafkaService {
    private KafkaTemplate<String,String> kafkaTemplate;
    private final Logger logger= LoggerFactory.getLogger(KafkaService.class);

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean updateLocation(String location){
        this.kafkaTemplate.send(AppConstants.LOCATION_TOPIC_NAME,location);
        this.logger.info("Location Produce");
        return true;
    }
}
