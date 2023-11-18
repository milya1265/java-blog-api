package com.example.api1.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private String id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password_hash")
    private String passwordHash;

    public User(String username, String password) {
        this.username = username;
        this.passwordHash = password;
    }
}
