package com.spring.demo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringDemoGatewayApplication {

  private static final String baseURL = "http://httpbin.org:80";

  public static void main(String[] args) {
    SpringApplication.run(SpringDemoGatewayApplication.class, args);
  }

  @Bean
  public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder) {
    return routeLocatorBuilder
        .routes()
        .route(predicateSpec -> predicateSpec.path("/get").uri(baseURL))
        .build();
  }
}
