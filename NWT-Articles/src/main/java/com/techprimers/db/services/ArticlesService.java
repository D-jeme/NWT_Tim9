package com.techprimers.db.services;

import com.techprimers.db.repository.ArticlesRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.techprimers.db.exceptions.NotFoundException;

import com.techprimers.db.model.Articles;

import java.util.Collection;

public class ArticlesService {
    private ArticlesRepository articlesRepository;
    public ResponseEntity<?> readArticles(){

        Collection<Articles> articles = this.articlesRepository.findAll();
        if(articles.isEmpty())
        {
            return new ResponseEntity(new NotFoundException().DisplayErrorMessage("There is no articles in the database"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<Articles>>(articles, HttpStatus.OK);
    }
}
