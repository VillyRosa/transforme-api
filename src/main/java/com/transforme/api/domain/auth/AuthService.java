package com.transforme.api.domain.auth;

import com.transforme.api.domain.user.User;
import com.transforme.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(LoginDTO login) {
        var user = this.userRepository.findByEmail(login.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(login.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return this.jwtService.generateToken(user.getId());
    }

    public void register(RegisterDTO register) {
        if (!register.password().equals(register.confirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        String hashedPassword = this.passwordEncoder.encode(register.password());

        userRepository.save(new User(register, hashedPassword));
    }

    public UUID getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof UUID)) {
            throw new RuntimeException("User is not authenticated");
        }

        return (UUID) authentication.getPrincipal();
    }

}
