package com.example.api1.api.dtos;

import lombok.Data;

@Data
public class AuthRequestBody {
    private String username;
    private String password;

}
