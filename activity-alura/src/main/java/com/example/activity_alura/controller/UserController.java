package com.example.activity_alura.controller;

import com.example.activity_alura.domain.user.UserDTO;
import com.example.activity_alura.service.UserService;
import com.example.activity_alura.domain.user.UserRequestDTO;
import com.example.activity_alura.domain.user.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO body) {
        return userService.createUser(body);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        var users = userService.findAllUsers();
        return ResponseEntity.ok().body(users);

    }
}


