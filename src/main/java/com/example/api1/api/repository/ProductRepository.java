package com.example.api1.api.repository;

import com.example.api1.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<User, String> {
    List<User> findUserById(String id);
//    List<User> d
}
