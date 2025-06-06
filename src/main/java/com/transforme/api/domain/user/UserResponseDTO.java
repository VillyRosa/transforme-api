package com.transforme.api.domain.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(
    UUID id,
    String firstname,
    String lastname,
    LocalDate birthdate,
    String email,
    LocalDateTime emailVerifiedAt,
    int heightCm,
    UserStatus status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getFirstname(), user.getLastname(), user.getBirthdate(), user.getEmail(), user.getEmailVerifiedAt(), user.getHeightCm(), user.getStatus(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
