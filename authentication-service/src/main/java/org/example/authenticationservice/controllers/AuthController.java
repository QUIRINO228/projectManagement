package org.example.authenticationservice.controllers;

import lombok.AllArgsConstructor;
import org.example.authenticationservice.model.AuthRequest;
import org.example.authenticationservice.model.AuthResponse;
import org.example.authenticationservice.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/authenticate")
    public AuthResponse authenticate(@RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }
}