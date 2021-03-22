package com.peasch.webbooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.peasch.webbooks")
public class WebbooksApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebbooksApplication.class, args);
    }

}
