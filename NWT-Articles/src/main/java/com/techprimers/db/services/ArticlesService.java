package com.techprimers.db.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techprimers.db.model.Users;
import com.techprimers.db.repository.ArticlesRepository;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.techprimers.db.exceptions.NotFoundException;
import org.slf4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.LoggerFactory;
import com.techprimers.db.model.Articles;

import java.io.IOException;
import java.util.Collection;

public class ArticlesService {
    private ArticlesRepository articlesRepository;
    private final Logger logger=LoggerFactory.getLogger(this.getClass());


    @RabbitListener(queues = "#{userCreatedQueue.name}")
    public void getMessage(String userCreatedMessage){

        logger.info(userCreatedMessage);
        ObjectMapper objectMapper=new ObjectMapper();
        Users user = null;
        try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            user = objectMapper.readValue(userCreatedMessage, Users.class);
        } catch (IOException e) {
            logger.info(String.valueOf(e.getMessage()));
        }

        logger.debug("User {} saved to DB", user.toString());
    }



    public ResponseEntity<?> readArticles(){

        Collection<Articles> articles = this.articlesRepository.findAll();
        if(articles.isEmpty())
        {
            return new ResponseEntity(new NotFoundException().DisplayErrorMessage("There is no articles in the database"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<Articles>>(articles, HttpStatus.OK);
    }
}
