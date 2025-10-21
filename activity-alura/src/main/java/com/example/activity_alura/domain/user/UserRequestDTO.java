package com.example.activity_alura.domain.user;

public record UserRequestDTO(
        String name,
        String email,
        String userName,
        String password,
        String cpf,
        String phone
)
{
}
