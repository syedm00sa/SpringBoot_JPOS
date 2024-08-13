package com.users.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("bank_route", r -> r.path("/api/bank/**")
                        .uri("http://bank-service:8081"))
                .route("user_route", r -> r.path("/api/user/**")
                        .uri("http://user-service:8082"))
                .build();
    }
}
