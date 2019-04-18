package com.techprimers.db.repository;

import com.techprimers.db.model.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ArticlesRepository extends JpaRepository<Articles, Integer> {
    Articles findByBroj(Integer broj);
    Articles findByNaziv(String naziv);
}
