package com.techprimers.db.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techprimers.db.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Collection;

public class UserServices {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());


    @RabbitListener(queues = "#{articleCreatedQueue.name}")
    public void getMessage(String articleCreatedMessage){

        logger.info(articleCreatedMessage);
        ObjectMapper objectMapper=new ObjectMapper();
        String article_id = null;
        try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            article_id = objectMapper.readValue(articleCreatedMessage, String.class);
        } catch (IOException e) {
            logger.info(String.valueOf(e.getMessage()));
        }

        logger.debug("Article {} deleted from DB", article_id);
    }




}

