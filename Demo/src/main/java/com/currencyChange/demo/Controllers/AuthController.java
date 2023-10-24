package com.currencyChange.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.currencyChange.demo.Dtos.StandardResponseDTO;
import com.currencyChange.demo.Models.LoginRequest;
import com.currencyChange.demo.Models.TokenResponse;
import com.currencyChange.demo.Services.JwtAuthService;
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtAuthService jwtAuthService;

    @PostMapping("/login")
    public ResponseEntity<StandardResponseDTO> authenticate(@RequestBody LoginRequest loginRequest) {
        String token = jwtAuthService.authenticateAndGenerateToken(loginRequest.getUsername(), loginRequest.getPassword());
        if (token != null) {
            return new ResponseEntity<>(new StandardResponseDTO().FullSuccess(token),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new StandardResponseDTO().FailSuccess("Authentication failed"),HttpStatus.OK);
        }
    }
}
