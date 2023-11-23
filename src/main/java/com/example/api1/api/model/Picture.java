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
@Table(name = "pictures")
public class Picture {
    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    String id;

    @Column(name = "uri", nullable = false, unique = true)
    String uri;

    @Column(name = "file_type", nullable = false)
    String fileType;
}
