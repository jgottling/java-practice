package com.spring.demo.configSV;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
@RefreshScope
public class SpringDemoConfigServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringDemoConfigServerApplication.class, args);
  }
}
