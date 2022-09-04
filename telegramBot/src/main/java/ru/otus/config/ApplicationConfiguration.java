package ru.otus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.telegram.telegrambots.TelegramBotsApi;


@Configuration
@Data
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationConfiguration {

    private String PROXY;
    private String CHECK;
    private String SOCKS_HOST;
    private String HOST;
    private String SOCKS_PORT;
    private String PORT;

    private String BOT_USER_NAME;
    private String TOKEN;
    private String CHECK_FLAG;
    private String PATH_THE_PHOTO;
    private String TYPE_FILE_PNG;
    private String TYPE_FILE_MP4;


    @Bean
    public TelegramBotsApi telegramBotsApi() {
        return new TelegramBotsApi();
    }

}
