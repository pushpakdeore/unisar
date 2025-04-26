package com.cap.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cap.main.repository")
public class CapAssistApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapAssistApplication.class, args);
    }
}
