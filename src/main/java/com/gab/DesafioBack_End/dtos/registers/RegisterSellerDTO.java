package com.gab.DesafioBack_End.dtos.registers;

import java.math.BigDecimal;

public record RegisterSellerDTO(
        String name,
        String cnpj,
        String email,
        String password,
        BigDecimal amount) {
}
