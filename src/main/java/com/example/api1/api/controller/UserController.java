package com.example.api1.api.controller;

import com.example.api1.api.model.User;
import com.example.api1.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

import java.util.ArrayList;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService s){
        this.userService = s;
    }

    @RequestMapping(value = "/user/{userID}", method = RequestMethod.GET)
    public User userForId(@PathVariable String userID) {
        return userService.getById(userID);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ArrayList<User> getAll() {
        return (ArrayList<User>)  userService.getAll();
    }


    @RequestMapping(value = "/user/{userID}", method = RequestMethod.DELETE)
    public ResponseEntity<String> Delete(@PathVariable String userID){
        userService.delete(userID);
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/user/{userID}", method = RequestMethod.PUT)
    public ResponseEntity<String> Update(@PathVariable String userID, @RequestBody User user){
        userService.update(userID, user.getUsername(), user.getUsername());
        return ResponseEntity.ok("success");
    }
}
