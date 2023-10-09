package com.example.api1.api.service;

import com.example.api1.api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList;

    UserService() {
        userList = new ArrayList<>();

        User user1 = new User(  "Vasya", "lolkek@mail.ru", "qwerty");
        User user2 = new User( "John", "john@example.com", "qwerty");
        User user3 = new User( "Alice", "alice@example.com", "qwerty");
        User user4 = new User( "Bob", "bob@example.com", "qwerty");
        User user5 = new User(  "Elena", "elena@example.com", "qwerty");
        User user6 = new User(  "Michael", "michael@example.com", "qwerty");
        User user7 = new User(  "Sara", "sara@example.com", "qwerty");
        User user8 = new User(  "David", "david@example.com", "qwerty");
        User user9 = new User(  "Olivia", "olivia@example.com", "qwerty");

        userList.addAll(Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9));

    }

    public Optional<User> getUser(String id) {
        Optional optional = Optional.empty();
        for (User u: userList){
            if (u.getId() == id){
                optional = Optional.of(u);
                return optional;
            }
        }
        return optional;
    }

    public List<User> getUsers() {
        return userList;
    }
}
