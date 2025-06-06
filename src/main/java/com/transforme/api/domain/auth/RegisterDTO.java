package com.transforme.api.domain.auth;

public record RegisterDTO(
    String firstname,
    String lastname,
    String email,
    String password,
    String confirmPassword
) {
}
