package com.example.api1.api.DTO;

import lombok.Data;

@Data
public class CreateUserRequestBody {
    private String username;
    private String password;
}
