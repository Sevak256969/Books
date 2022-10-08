package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class MovieRecommenderSystemApplication {
    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(MovieRecommenderSystemApplication.class, args);
    }
}
