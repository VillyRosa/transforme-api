package com.transforme.api.domain.user;

import com.transforme.api.domain.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO findMyAccount() {
        var user = this.authService.getAuthenticatedUser();
        return new UserResponseDTO(user);
    }

    public UserResponseDTO updateMyAccount(UpdateUserDTO updateUser) {
        var user = this.authService.getAuthenticatedUser();

        user.setFirstname(updateUser.firstname());
        user.setLastname(updateUser.lastname());
        user.setEmail(updateUser.email());
        user.setBirthdate(updateUser.birthdate());
        user.setHeightCm(updateUser.heightCm());
        user.setUpdatedAt(LocalDateTime.now());

        return new UserResponseDTO(this.userRepository.save(user));
    }

    public void updateMyPassword(UpdatePasswordDTO newPassword) {
        var user = this.authService.getAuthenticatedUser();

        if (!this.passwordEncoder.matches(newPassword.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        if (!newPassword.newPassword().equals(newPassword.confirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        user.setPassword(this.passwordEncoder.encode(newPassword.newPassword()));
        this.userRepository.save(user);
    }

    public void deleteMyAccount() {
        UUID userId = this.authService.getAuthenticatedUserId();
        this.userRepository.deleteById(userId);
    }

}
