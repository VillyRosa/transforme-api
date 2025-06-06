package com.transforme.api.domain.user;

import com.transforme.api.domain.auth.RegisterDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstname;

    private String lastname;

    private LocalDate birthdate;

    private String email;

    private LocalDateTime emailVerifiedAt;

    private String password;

    private int heightCm;

    private UserStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public User(RegisterDTO newUser, String hashedPassword) {
        this.firstname = newUser.firstname();
        this.lastname = newUser.lastname();
        this.email = newUser.email();
        this.password = hashedPassword;
        this.status = UserStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
