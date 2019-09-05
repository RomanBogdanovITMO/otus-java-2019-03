package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class Application {
    static {
        ApiContextInitializer.init();
    }
    public static void main(String[] args) {
        //простые настройки для обхода блокировки в телеграме
        System.getProperties().put( "proxySet", "true" );
        System.getProperties().put( "socksProxyHost", "127.0.0.1" );
        System.getProperties().put( "socksProxyPort", "9150" );

        SpringApplication.run(Application.class, args);
    }
}
