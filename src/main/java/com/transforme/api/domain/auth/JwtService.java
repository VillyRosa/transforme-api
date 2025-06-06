package com.transforme.api.domain.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    @Value("security.jwt.secret")
    private String secret;

    public String generateToken(UUID userId) {
        Instant now = Instant.now();
        Instant expiresAt = now.plus(2, ChronoUnit.HOURS);

        return JWT.create()
                .withIssuer("transforme-api")
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(expiresAt))
                .withSubject(userId.toString())
                .sign(Algorithm.HMAC256(secret));
    }

    public UUID extractUserId(String jwt) {
        return UUID.fromString(JWT.decode(jwt).getSubject());
    }
}
