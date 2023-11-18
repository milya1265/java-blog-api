package com.example.api1.api.controller;

import com.example.api1.api.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
public class PictureController {

    private PictureService pictureService;

    @Autowired
    public PictureController(PictureService s) {
        this.pictureService = s;
    }
    @PostMapping("/upload")
    public String add(
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (file != null && !file.getOriginalFilename().isEmpty()) {

            String resultFileName =  pictureService.Store(file);


            return "{\"uri\": \"" + resultFileName + "\"}";

        }
        return "{\"error\": \"file is empty\"}";
    }
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws MalformedURLException {

        Resource file = pictureService.loadAsFile(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION  ,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
