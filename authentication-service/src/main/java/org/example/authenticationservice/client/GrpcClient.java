package org.example.authenticationservice.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.example.auth.AuthServiceGrpc;
import org.example.auth.AuthServiceOuterClass;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GrpcClient {

    private final ManagedChannel channel;
    private final AuthServiceGrpc.AuthServiceBlockingStub authServiceStub;

    public GrpcClient() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9091)
                .usePlaintext()
                .build();
        authServiceStub = AuthServiceGrpc.newBlockingStub(channel);
    }

    public AuthServiceOuterClass.AuthResponse authenticate(AuthServiceOuterClass.AuthRequest request) {
        try {
            return authServiceStub.authenticate(request);
        } catch (StatusRuntimeException e) {
            log.error("gRPC request failed: {}", e.getStatus(), e);
            throw e;
        }
    }

    @PreDestroy
    public void shutdown() {
        log.info("Shutting down gRPC channel");
        channel.shutdown();
    }
}