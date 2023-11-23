package com.example.api1.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostReq {
    String author;
    String text;
    ArrayList<String> photosID;
}
