package com.spring.demo.usersMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@FeignClient
public class SpringDemoUsersMsApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringDemoUsersMsApplication.class, args);
  }
}
