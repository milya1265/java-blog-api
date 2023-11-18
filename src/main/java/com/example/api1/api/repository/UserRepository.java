package com.example.api1.api.repository;

import com.example.api1.api.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository <User, String> {
   @Override
   Optional<User> findById(String id);
   Optional<User> findByUsername(String username);

}
