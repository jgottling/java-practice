package com.spring.demo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
public class SpringDemoGatewayApplication {

  private static final String baseURL = "http://httpbin.org:80";
  private static final String usersMSURL = "http://usersMS/users/v1";

  public static void main(String[] args) {
    SpringApplication.run(SpringDemoGatewayApplication.class, args);
  }

  @Bean
  public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder) {
    return routeLocatorBuilder

        .routes().route("users_health", p -> p.path("/users/health").uri(usersMSURL + "/monitoring"))
        .route("users_owners", p -> p.path("/users/owners/**").uri(usersMSURL + "/owners"))
        .route(predicateSpec -> predicateSpec.path("/get").uri(baseURL))
        .build();
  }
}
