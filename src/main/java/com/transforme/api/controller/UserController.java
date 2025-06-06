package com.transforme.api.controller;

import com.transforme.api.domain.user.UpdatePasswordDTO;
import com.transforme.api.domain.user.UpdateUserDTO;
import com.transforme.api.domain.user.UserResponseDTO;
import com.transforme.api.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMyAccount() {
        return ResponseEntity.ok().body(userService.findMyAccount());
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateMyAccount(@RequestBody UpdateUserDTO updateUser) {
        return ResponseEntity.ok().body(userService.updateMyAccount(updateUser));
    }

    @PutMapping("/me/password")
    public ResponseEntity<Void> updateMyPassword(@RequestBody UpdatePasswordDTO newPassword) {
        this.userService.updateMyPassword(newPassword);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMyAccount() {
        this.userService.deleteMyAccount();
        return ResponseEntity.ok().build();
    }

}
