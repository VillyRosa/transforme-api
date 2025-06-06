package com.transforme.api.controller;

import com.transforme.api.domain.auth.AuthService;
import com.transforme.api.domain.auth.LoginDTO;
import com.transforme.api.domain.auth.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO login) {
        return ResponseEntity.ok(authService.login(login));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDTO register) {
        this.authService.register(register);
        return ResponseEntity.ok().build();
    }

}
