package com.example.api1.api.controller;

import com.example.api1.api.model.User;
import com.example.api1.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
//@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService s){
        this.userService = s;
    }

    @RequestMapping(value = "/user/{userID}", method = RequestMethod.GET)
    public User myProfile(@PathVariable String userID) {
        return userService.getById(userID);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<String> newProfile(@RequestBody User user){
        userService.create(user);
        return ResponseEntity.ok("success");
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
        userService.update(userID, user.getEmail(), user.getUsername());
        return ResponseEntity.ok("success");
    }
}
