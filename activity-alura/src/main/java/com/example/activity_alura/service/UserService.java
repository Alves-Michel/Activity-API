package com.example.activity_alura.service;

import com.example.activity_alura.domain.user.UserDTO;
import com.example.activity_alura.repository.UserRepository;
import com.example.activity_alura.domain.user.User;
import com.example.activity_alura.domain.user.UserRequestDTO;
import com.example.activity_alura.domain.user.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    public ResponseEntity createUser(@RequestBody UserRequestDTO body) {
        Optional<User> user = this.userRepository.findByUserName(body.name());

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setName(body.name());
            newUser.setEmail(body.email());
            newUser.setUserName(body.userName());
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setCpf(body.cpf());
            newUser.setPhone(body.phone());

            this.userRepository.save(newUser);

            return ResponseEntity.ok(new UserResponseDTO(newUser.getName(), newUser.getEmail()));

        }

        return ResponseEntity.badRequest().build();

    }


    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(
                        user.getName(),
                        user.getEmail(),
                        user.getUserName(),
                        user.getCpf(),
                        user.getPhone()

                        )).collect(Collectors.toList());
    }

}
