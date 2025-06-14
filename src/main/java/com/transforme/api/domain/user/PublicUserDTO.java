package com.transforme.api.domain.user;

import java.time.LocalDate;
import java.util.UUID;

public record PublicUserDTO(
    UUID id,
    String firstname,
    String lastname,
    LocalDate birthdate,
    String email,
    int heightCm
) {
    public PublicUserDTO(User user) {
        this(user.getId(), user.getFirstname(), user.getLastname(), user.getBirthdate(), user.getEmail(), user.getHeightCm());
    }
}
