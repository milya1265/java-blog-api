package com.example.api1.api.repository;

import com.example.api1.api.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    @Override
    Optional<Post> findById(Integer id);

    Iterable<Post> findAllByOrderByTimeDesc();
    @Query(nativeQuery = true, value = "SELECT * FROM posts ORDER BY time DESC LIMIT (5) OFFSET (5* :numPage);")
    Iterable<Post> findAllBySNumber(@Param("numPage") Integer numPage);

}