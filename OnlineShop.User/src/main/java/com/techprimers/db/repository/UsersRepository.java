package com.techprimers.db.repository;

import com.techprimers.db.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findById(Long id);
    void deleteById(Long id);
    Users findByEmail(String email);
}
