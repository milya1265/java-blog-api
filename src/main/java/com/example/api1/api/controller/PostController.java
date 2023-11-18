package com.example.api1.api.controller;

import com.example.api1.api.DTO.PostReq;
import com.example.api1.api.DTO.PostRes;
import com.example.api1.api.model.Post;
import com.example.api1.api.service.PictureService;
import com.example.api1.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class PostController {
    private PostService postService;
    private PictureService pictureService;

    @Autowired
    public PostController(PostService s) {
        this.postService = s;
    }

    @RequestMapping(value = "/post/{postID}", method = RequestMethod.GET)
    public Post getPost(@PathVariable Integer postID) {
        Post mainPost =  postService.getById(postID);
        return mainPost;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<String> newPost(@RequestBody PostReq postReq) { //@RequestParam("file") MultipartFile file
        Post post = Post.builder()
                .text(postReq.getText())
                .author(postReq.getAuthor())
                .time(new Date())
                .build();

        post = postService.create(post);
        postService.connectPostToPhoto(post.getId(), postReq.getPhotosID());


        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ArrayList<PostRes> getAll() {
        return postService.getFeed();
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

    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public ArrayList<PostRes> getFeedController() {
        return postService.getFeed();
    }


}

