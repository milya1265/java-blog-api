package com.example.api1.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostRes {
    Integer id;
    String author;
    Date time;
    String text;
    ArrayList<String> photos;
}
