package com.gab.DesafioBack_End.dtos.registers;

import java.math.BigDecimal;

public record RegisterUsersDTO(
        String name,
        String cpf,
        String email,
        String password,
        BigDecimal amount) {
}
