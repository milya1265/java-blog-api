package com.example.api1.api.repository;

import com.example.api1.api.model.PostToPicture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostToPictureRepository extends CrudRepository<PostToPicture, Long> {
    @Query
    List<PostToPicture> findPostToPicturesByIdPost(Integer PostId);
}
