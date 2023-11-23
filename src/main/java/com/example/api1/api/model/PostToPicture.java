package com.example.api1.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post_to_pictures")
public class PostToPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "id_post", nullable = false)
    Integer idPost;

    @Column(name = "uri", nullable = false)
    String idPhoto;

}
