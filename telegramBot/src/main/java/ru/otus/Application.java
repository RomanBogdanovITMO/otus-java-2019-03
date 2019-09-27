package ru.otus;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import ru.otus.util.PropertiesHelper;

import java.util.Properties;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private  ApplicationConfiguration myConfig;


    /* private static final String PROXY;
    private static final String CHECK;
    private static final String SOCKS_HOST;
    private static final String HOST;
    private static final String SOCKS_PORT;
    private static final String PORT;


    static {
        ApiContextInitializer.init();
        Properties properties = PropertiesHelper.getProperties("telegrambot.properties");
        PROXY = properties.getProperty("telegrambot.proxy");
        PROXY =
        CHECK = properties.getProperty("telegrambot.check");
        SOCKS_HOST = properties.getProperty("telegrambot.socksHost");
        HOST = properties.getProperty("telegrambot.host");
        SOCKS_PORT = properties.getProperty("telegrambot.socksPort");
        PORT = properties.getProperty("telegrambot.port");

    }*/


    public static void main(String[] args) {
        //простые настройки для обхода блокировки в телеграме
        /*System.getProperties().put(PROXY, CHECK);
        System.getProperties().put(SOCKS_HOST, HOST);
        System.getProperties().put(SOCKS_PORT, PORT);*/

        /*System.getProperties().put(myConfig.getPROXY(), myConfig.getCHECK());
        System.getProperties().put(myConfig.getSOCKS_HOST(), myConfig.getHOST());
        System.getProperties().put(myConfig.getSOCKS_PORT(), myConfig.getPORT());*/

        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        System.getProperties().put(myConfig.getPROXY(), myConfig.getCHECK());
        System.getProperties().put(myConfig.getSOCKS_HOST(), myConfig.getHOST());
        System.getProperties().put(myConfig.getSOCKS_PORT(), myConfig.getPORT());
    }


}
