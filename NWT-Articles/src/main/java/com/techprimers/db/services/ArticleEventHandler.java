package com.techprimers.db.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techprimers.db.model.Articles;
import com.techprimers.db.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;


@Component
@RepositoryEventHandler
public class ArticleEventHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private RabbitTemplate rabbitTemplate;
    private Queue userCreatedQueue;

    @Autowired
    public ArticleEventHandler(RabbitTemplate rabbitTemplate, Queue userCreatedQueue){
        this.rabbitTemplate = rabbitTemplate;
        this.userCreatedQueue = userCreatedQueue;
    }

    @HandleAfterCreate
    public void handleAfterCreated(String id) {

        rabbitTemplate.convertAndSend(userCreatedQueue.getName(), id);
    }

    private String serializeToJson(String id) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(id);
        } catch (JsonProcessingException e) {
            logger.info(String.valueOf(e));
        }
        logger.debug("Serijalizirana poruka: {}", jsonInString);
        return jsonInString;
    }
}

