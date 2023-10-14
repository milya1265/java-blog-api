package com.example.api1.api.service;

import com.example.api1.api.model.User;
import com.example.api1.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    private UserRepository userRepository;
    @Autowired
    UserService(UserRepository repository ) {
        userRepository = repository;
    }

    public User getById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public void newUser(User user) {
        userRepository.save(user);
    }

    public void update(String userID, String email, String username){
        User user = userRepository.findById(userID).orElse(null);
        if (user != null) {
            user.setEmail(email);
            user.setUsername(username);
            userRepository.save(user);
        }
    }
    public void delete(String userID){
        userRepository.deleteById(userID);
    }

}
