package com.example.api1.api.repository;

import com.example.api1.api.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    @Override
    Optional<Post> findById(Integer id);
}