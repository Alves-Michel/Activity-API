package com.example.activity_alura.domain.user;

import jakarta.validation.constraints.Pattern;

public record UserRequestDTO(
        String name,
        String email,
        String userName,
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "A senha deve ter no mínimo 8 caracteres, incluindo 1 letra maiúscula, 1 número e 1 caractere especial."
        )
        String password,
        String cpf,
        String phone
)
{
}
