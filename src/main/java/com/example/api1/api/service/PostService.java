package com.example.api1.api.service;

import com.example.api1.api.model.Post;
import com.example.api1.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository repo){
        postRepository = repo;
    }


    public Post getById(Integer postID) {
        return postRepository.findById(postID).orElse(null);
    }

    public void create(Post post) {
        postRepository.save(post);
    }

    public Iterable<Post> getAll() {
        return postRepository.findAll();
    }

    public void delete(Integer postID) {
        postRepository.deleteById(postID);
    }

    public void update(Integer userID, String text) {
        Post post = postRepository.findById(userID).orElse(null);
        if (post != null) {
            post.setText(text);
            postRepository.save(post);
        }
    }

}
