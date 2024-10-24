package org.example.users.services.user;

import org.example.auth.AuthServiceOuterClass;

import org.example.users.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    User authenticate(String email, String password);
    AuthServiceOuterClass.AuthResponse authenticate(AuthServiceOuterClass.AuthRequest authRequest);
}