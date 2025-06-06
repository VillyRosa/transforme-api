package com.transforme.api.domain.user;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateUserDTO(
    String firstname,
    String lastname,
    String email,
    LocalDate birthdate,
    int heightCm
) {
}
