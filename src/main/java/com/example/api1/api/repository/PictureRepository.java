package com.example.api1.api.repository;

import com.example.api1.api.model.Picture;
import org.springframework.data.repository.CrudRepository;

public interface PictureRepository extends CrudRepository<Picture, Integer> {
}