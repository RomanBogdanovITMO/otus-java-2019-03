package ru.otus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.TelegramBotsApi;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi(){
        return new TelegramBotsApi();
    }
}
