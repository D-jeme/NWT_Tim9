package com.techprimers.db.services;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techprimers.db.model.Users;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.amqp.core.Queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.rest.core.annotation.HandleAfterCreate;

import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler

public class UserEventHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private RabbitTemplate rabbitTemplate;
    private Queue userCreatedQueue;

    @Autowired
    public UserEventHandler(RabbitTemplate rabbitTemplate, Queue userCreatedQueue){
        this.rabbitTemplate = rabbitTemplate;
        this.userCreatedQueue = userCreatedQueue;
    }

    @HandleAfterCreate
    public void handleAfterCreated(Users user) {

        rabbitTemplate.convertAndSend(userCreatedQueue.getName(), serializeToJson(user));
    }

    private String serializeToJson(Users user) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            logger.info(String.valueOf(e));
        }
        logger.debug("Serialized message payload: {}", jsonInString);
        return jsonInString;
    }
}
