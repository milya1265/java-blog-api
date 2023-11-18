package com.example.api1.api.service;

import com.example.api1.api.model.User;
import com.example.api1.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService  {

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

    public void create(User user) {
        userRepository.save(user);
    }

    public void update(String userID, String email, String username){
        User user = userRepository.findById(userID).orElse(null);
        if (user != null) {
            user.setUsername(username);
            userRepository.save(user);
        }
    }
    public void delete(String userID){
        userRepository.deleteById(userID);
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).orElseThrow( () -> new UsernameNotFoundException(
                "User not found"
        ));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPasswordHash(),
                Collections.singleton(new SimpleGrantedAuthority("USER"))
        );
    }
}
