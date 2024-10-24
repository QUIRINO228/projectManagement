package org.example.users.client;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.example.users.repositories.UserRepository;
import org.example.users.services.user.AuthServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Component
@Slf4j
public class GrpcServer {

    private Server server;

    private final UserRepository userRepository;

    public GrpcServer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void start() throws IOException {
        server = ServerBuilder.forPort(9091)
                .addService(new AuthServiceImpl(userRepository))
                .build()
                .start();
    }

    @PreDestroy
    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }
}