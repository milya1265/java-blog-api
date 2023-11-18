package com.example.api1.api.dtos;

import lombok.Data;

@Data
public class CreateUserRequestBody {
    private String username;
    private String password;
}
