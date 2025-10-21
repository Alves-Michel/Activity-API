package com.example.activity_alura.controller;

import com.example.activity_alura.service.RegisterUser;
import com.example.activity_alura.domain.user.UserRequestDTO;
import com.example.activity_alura.domain.user.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RegisterUser registerUser;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO body) {
        return registerUser.createUser(body);
    }
}
