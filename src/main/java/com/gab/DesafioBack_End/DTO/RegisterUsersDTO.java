package com.gab.DesafioBack_End.DTO;

import java.math.BigDecimal;

public record RegisterUsersDTO(
        String name,
        String cpf,
        String email,
        String password,
        String type,
        BigDecimal amount) {
}
