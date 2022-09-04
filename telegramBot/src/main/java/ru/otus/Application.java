package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import ru.otus.config.ApplicationConfiguration;

@SpringBootApplication
public class Application {

    static {
        ApiContextInitializer.init();
    }

    public Application(ApplicationConfiguration myConfig) {
        //простые настройки для обхода блокировки в телеграме
        System.getProperties().put(myConfig.getPROXY(), myConfig.getCHECK());
        System.getProperties().put(myConfig.getSOCKS_HOST(), myConfig.getHOST());
        System.getProperties().put(myConfig.getSOCKS_PORT(), myConfig.getPORT());

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

}
