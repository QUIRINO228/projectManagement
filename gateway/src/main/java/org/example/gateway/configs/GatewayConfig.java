package org.example.gateway.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {
    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("amazons3", r -> r.path("/amazons3/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://amazons3"))
                .route("users", r -> r.path("/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://users"))
                .route("authentication-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://authentication-service"))
                .route("projects", r -> r.path("/projects/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://projects"))
                .build();
    }
}
