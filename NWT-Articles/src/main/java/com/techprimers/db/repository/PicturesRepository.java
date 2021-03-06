package com.techprimers.db.repository;

import com.techprimers.db.model.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PicturesRepository extends JpaRepository<Pictures, Integer> {
    Pictures findByBroj(Integer broj);
    Pictures findBySlika(String slika);
}
