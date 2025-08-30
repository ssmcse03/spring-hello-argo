package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random; 

@SpringBootApplication
@RestController
public class Application {
    private boolean readFromFile = false; // Flag to toggle between hardcoded list and file
    private List<String> defaultNames = Arrays.asList( "Alice", "Bob", "Charlie", "Diana");
    private List<String> names;

    @Value("${greeting.message: Default_Greeting!}")
    private String greetingMessage;

    public String getGreetingMessage() {
        return greetingMessage;
    }


    public Application() {
        if (readFromFile) {
            try {
                names = Files.readAllLines(Paths.get("C:/ado/learning/spring-hello-argo/source/app/src/main/resources/names.txt"));
            } catch (IOException e) {
                names = defaultNames; // Fallback in case of error
                System.err.println("Error reading names from file: " + e.getMessage());
            }
        } else {
            names = defaultNames;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/")
    public String hello() {
        Random random = new Random();

        String randomName = names.get(random.nextInt(names.size()));
        String color = String.format("#%06x", random.nextInt(0xFFFFFF + 1));

        String greetingMessage = getGreetingMessage();
       
        return "<html><body>" +
           "Hello <b><span style='color:" + color + "'>" + randomName + "</span></b>!!!<br>" +
           "<b>" + greetingMessage + "</b><br>" +
           "Response from Spring Boot + Argo CD!" +
           "</body></html>";
    }
}
