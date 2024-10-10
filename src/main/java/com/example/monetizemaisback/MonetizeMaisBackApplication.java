package com.example.monetizemaisback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*", value = "*", allowedHeaders = "*")
public class MonetizeMaisBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonetizeMaisBackApplication.class, args);
    }

}
