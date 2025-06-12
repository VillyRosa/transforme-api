package com.transforme.api.domain.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterDTO(
    @NotBlank
    String firstname,

    @NotNull
    String lastname,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[._#@$!%*?&]).+$",
        message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    String password,

    @NotBlank
    String confirmPassword
) {
}
