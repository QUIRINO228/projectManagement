package org.example.users.services.user;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.auth.AuthServiceGrpc;
import org.example.auth.AuthServiceOuterClass;
import org.example.users.model.User;
import org.example.users.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase implements AuthService {


    private UserRepository userRepository;

    @Override
    public User authenticate(String email, String password) {
        log.info("Authenticating user: {}", email);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (BCrypt.checkpw(password, user.getPassword())) {
                log.info("User authenticated successfully: {}", email);
                return user;
            }
        }
        log.warn("Authentication failed for user: {}", email);
        return null;
    }

    @Override
    public AuthServiceOuterClass.AuthResponse authenticate(AuthServiceOuterClass.AuthRequest authRequest) {
        User authenticatedUser = authenticate(authRequest.getEmail(), authRequest.getPassword());
        if (authenticatedUser != null) {
            return AuthServiceOuterClass.AuthResponse.newBuilder()
                    .setUserId(authenticatedUser.getId())
                    .setEmail(authenticatedUser.getEmail())
                    .setRole(authenticatedUser.getRole().name())
                    .build();
        } else {
            throw new RuntimeException("Authentication failed");
        }
    }

    @Override
    public void authenticate(AuthServiceOuterClass.AuthRequest request, StreamObserver<AuthServiceOuterClass.AuthResponse> responseObserver) {
        try {
            log.info("Received gRPC request to authenticate user: {}", request.getEmail());
            AuthServiceOuterClass.AuthResponse response = authenticate(request);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error during gRPC authentication: {}", e.getMessage(), e);
            responseObserver.onError(e);
        }
    }
}