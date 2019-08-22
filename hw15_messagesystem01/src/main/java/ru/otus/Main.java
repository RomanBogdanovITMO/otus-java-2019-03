package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.ms.messageSystem.MessageSystem;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws InterruptedException {


        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        MessageSystem ms=context.getBean(MessageSystem.class);
        ms.start();
        System.out.println("hello");
    }
}
