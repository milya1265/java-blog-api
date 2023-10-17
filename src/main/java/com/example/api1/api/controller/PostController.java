package com.example.api1.api.controller;

import com.example.api1.api.model.Post;
import com.example.api1.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService s) {
        this.postService = s;
    }


    @RequestMapping(value = "/post/{postID}", method = RequestMethod.GET)
    public Post getPost(@PathVariable Integer postID) {
        return postService.getById(postID);
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<String> newPost(@RequestBody Post post
                                          ) { //@RequestParam("file") MultipartFile file

        post.setTime(new Date());
        postService.create(post);
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ArrayList<Post> getAll() {
        return (ArrayList<Post>) postService.getAll();
    }


    @RequestMapping(value = "/post/{postID}", method = RequestMethod.DELETE)
    public ResponseEntity<String> Delete(@PathVariable Integer postID){
        postService.delete(postID);
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/post/{postID}", method = RequestMethod.PUT)
    public ResponseEntity<String> Update(@PathVariable Integer postID, @RequestBody Post post){
        postService.update(postID, post.getText());
        return ResponseEntity.ok("success");
    }
}

