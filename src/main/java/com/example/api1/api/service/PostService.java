package com.example.api1.api.service;

import com.example.api1.api.DTO.PostRes;
import com.example.api1.api.model.Post;
import com.example.api1.api.model.PostToPicture;
import com.example.api1.api.model.User;
import com.example.api1.api.repository.PostRepository;
import com.example.api1.api.repository.PostToPictureRepository;
import com.example.api1.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private PostToPictureRepository postToPictureRepository;
    @Autowired
    public PostService(PostRepository repo, PostToPictureRepository repo2, UserRepository repo3){
        postRepository = repo;
        postToPictureRepository = repo2;
        userRepository = repo3;
    }


    public Post getById(Integer postID) {
        return postRepository.findById(postID).orElse(null);
    }

    public Post create(Post post) {
        Optional<User> u = userRepository.findByUsername(post.getAuthor());
        if (u.isPresent()){
            post.setAuthor(u.get().getId());
            return postRepository.save(post);
        }

        post.setAuthor("");
        post.setText("");
        post.setId(0);
        return post;
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

    public void connectPostToPhoto(Integer idPost, ArrayList<String> photos) {
        for (int i = 0; i < photos.size(); i++){
            PostToPicture entity = new PostToPicture();
            entity.setIdPost(idPost);
            entity.setIdPhoto(photos.get(i));
            postToPictureRepository.save(entity);
        }
    }
    public ArrayList<String> getPostPhotoURI(Integer postID){
        List<PostToPicture> arrEntity = postToPictureRepository.findPostToPicturesByIdPost(postID);
        ArrayList<String> photosID = new ArrayList<String>();
        for (int i = 0; i < arrEntity.size(); i++){
            photosID.add(arrEntity.get(i).getIdPhoto());
        }
        return photosID;
    }

    public ArrayList<PostRes> getFeed(Integer pageNum) {
        Iterable<Post> iterable = postRepository.findAllBySNumber(pageNum);
        ArrayList<PostRes> res = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        iterable.forEach(posts::add);
        for (int i = 0; i < posts.size(); i++){
            ArrayList<String> photos = getPostPhotoURI(posts.get(i).getId());

            Optional<User> u = userRepository.findById(posts.get(i).getAuthor());
            PostRes postRes = PostRes.builder()
                    .author(u.get().getUsername())
                    .time(posts.get(i).getTime())
                    .id(posts.get(i).getId())
                    .photos(photos)
                    .text(posts.get(i).getText())
                    .time(posts.get(i).getTime()).build();
            res.add(postRes);
        }


        return res;
    }

//    public ArrayList<PostDTO> getFeed(){
//
//    }
}
