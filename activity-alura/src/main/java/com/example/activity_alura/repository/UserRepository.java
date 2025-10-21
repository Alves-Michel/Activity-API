package com.example.activity_alura.repository;

import com.example.activity_alura.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String userName);
    Optional<User> findById(Long userId);
}
