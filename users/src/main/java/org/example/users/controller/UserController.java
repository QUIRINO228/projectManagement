package org.example.users.controller;



import org.example.auth.AuthServiceOuterClass;
import org.example.users.dto.UserDto;
import org.example.users.services.user.AuthService;
import org.example.users.services.user.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final AuthService authService;
    private final RegistrationService registrationService;

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody AuthServiceOuterClass.AuthRequest authRequest) {
        log.info("Authenticating user: {}", authRequest);
        try {
            AuthServiceOuterClass.AuthResponse response = authService.authenticate(authRequest);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Authentication failed"));
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
        boolean isCreated = registrationService.createUser(userDto);
        return isCreated
                ? ResponseEntity.ok().body(Map.of("message", "Registration successful"))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Registration failed"));
    }
}