package com.example.api1.api.controller;

import com.example.api1.api.model.User;
import com.example.api1.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService s){
        this.userService = s;
    }

    @GetMapping("/user")
    public User getUser(@RequestParam String id){
        Optional user = userService.getUser(id);
        if (user.isPresent()){
            return (User) user.get();
        }
        return null;
    }
    @GetMapping("/users")
    public Object[] getUsers(){
        List<User> users = userService.getUsers();
        return users.toArray();
    }

//    @PostMapping("/user")
}
