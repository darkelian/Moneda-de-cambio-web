package com.currencyChange.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.currencyChange.demo.Services.UserService;
import com.currencyChange.demo.Dtos.StandardResponseDTO;
import com.currencyChange.demo.Dtos.UserRegistrationDto;
import com.currencyChange.demo.Dtos.PasswordUpdateDto;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register-admin")
    public ResponseEntity<StandardResponseDTO> registerAdmin(@RequestBody UserRegistrationDto registrationDto) {
        userService.registerAdmin(registrationDto);
        return ResponseEntity.ok(new StandardResponseDTO().FullSuccess("User registered successfully"));
    }

    @PutMapping("/update-password")
    public ResponseEntity<StandardResponseDTO> updatePassword(@RequestBody PasswordUpdateDto passwordUpdateDto, Principal principal) {
        boolean updated = userService.updatePassword(principal.getName(), passwordUpdateDto);
        if (updated) {
            return ResponseEntity.ok(new StandardResponseDTO().FullSuccess("Password updated successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardResponseDTO().FailSuccess("Failed to update password"));
        }
    }
}
