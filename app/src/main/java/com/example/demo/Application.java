package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Arrays;
import java.util.List;
import java.util.Random; 

@SpringBootApplication
@RestController
public class Application {
    private List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana");

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/")
    public String hello() {
        Random random = new Random();
        String randomName = names.get(random.nextInt(names.size()));
        return "Hello " + randomName + " from Spring Boot + Argo CD!";    
    }
}
