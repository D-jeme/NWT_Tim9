package com.techprimers.db.repository;

import com.techprimers.db.model.Korpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorpaRepository extends JpaRepository<Korpa,Integer> {

    Korpa findById(int id);
}
