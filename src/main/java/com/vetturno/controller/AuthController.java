package com.vetturno.controller;

import com.vetturno.dto.AuthResponse;
import com.vetturno.dto.LoginRequest;
import com.vetturno.dto.RegistroRequest;
import com.vetturno.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registrar(
            @RequestBody RegistroRequest request) {

        return ResponseEntity.ok(
                authService.registrar(request)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}