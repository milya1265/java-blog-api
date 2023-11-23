package com.example.api1.api.controller;
import com.example.api1.api.DTO.*;
import com.example.api1.api.model.User;
import com.example.api1.api.service.UserService;
import com.example.api1.api.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequestBody authRequestBody) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestBody.getUsername(), authRequestBody.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Invalid password or username"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequestBody.getUsername());
        String token =  tokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestBody createUserRequestBody) {

        if (!userService.findByUsername(createUserRequestBody.getUsername()).isEmpty()) {
            System.out.println("РУКИ ИЗ ЖОПЫ");
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Руки из жопы достань"), HttpStatus.BAD_REQUEST);
        }

        User user = new User(createUserRequestBody.getUsername(), passwordEncoder.encode(createUserRequestBody.getPassword()));
        System.out.println(user);
        userService.create(user);
        return ResponseEntity.ok(new CreateUserResponse(user.getId(), user.getUsername()));
    }
}
