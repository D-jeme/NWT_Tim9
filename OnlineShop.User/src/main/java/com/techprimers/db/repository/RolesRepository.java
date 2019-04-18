package com.techprimers.db.repository;

import com.techprimers.db.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Roles findByTip(String tip);
    void deleteById(Long id);
    Roles findById(Long id);
}
