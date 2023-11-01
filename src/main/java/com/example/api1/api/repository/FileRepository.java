package com.example.api1.api.repository;

import com.example.api1.api.model.FilePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FilePost, Long> {
}