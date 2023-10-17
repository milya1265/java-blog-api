package com.example.api1.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    Integer id;
    @Column(name = "author", nullable = false)
    String author;
    @Column(name = "time", nullable = false)
    Date time;
    @Column(name = "text")
    String text;
    @Column(name = "cnt_of_files")
    Integer countOfFiles;

}
