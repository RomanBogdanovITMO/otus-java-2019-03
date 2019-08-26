package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MSGDemo {
    public static void main(String[] args) {
        SpringApplication.run(MSGDemo.class, args);
        System.out.println("hello");
    }
}
