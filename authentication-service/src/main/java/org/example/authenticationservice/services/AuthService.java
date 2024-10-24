package org.example.authenticationservice.services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.auth.AuthServiceOuterClass;
import org.example.authenticationservice.client.GrpcClient;
import org.example.authenticationservice.model.AuthRequest;
import org.example.authenticationservice.model.AuthResponse;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final GrpcClient grpcClient;
    private final JwtUtil jwtUtil;

    public AuthResponse authenticate(AuthRequest request) {
        try {

            AuthServiceOuterClass.AuthRequest grpcRequest = AuthServiceOuterClass.AuthRequest.newBuilder()
                    .setEmail(request.getEmail())
                    .setPassword(request.getPassword())
                    .build();
            log.info("Sending gRPC request to authenticate user: {}", grpcRequest.getEmail());


            AuthServiceOuterClass.AuthResponse grpcResponse = grpcClient.authenticate(grpcRequest);


            if (grpcResponse != null) {
                String role = grpcResponse.getRole().isEmpty() ? "USER" : grpcResponse.getRole();
                String accessToken = jwtUtil.generate(grpcResponse.getUserId(), role, "ACCESS");
                String refreshToken = jwtUtil.generate(grpcResponse.getUserId(), role, "REFRESH");
                log.info("User authenticated successfully: {}", grpcResponse.getEmail());
                return new AuthResponse(accessToken, refreshToken);
            } else {
                log.error("gRPC response is null");
                throw new RuntimeException("User authentication failed: gRPC response is null");
            }
        } catch (Exception ex) {
            log.error("User authentication failed: {}", ex.getMessage());
            throw new RuntimeException("User authentication failed: " + ex.getMessage(), ex);
        }
    }
}