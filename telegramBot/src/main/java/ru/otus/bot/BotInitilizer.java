package ru.otus.bot;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramBotsApi;

@Component
@RequiredArgsConstructor
public class BotInitilizer {

    private final TelegramBotsApi telegramBotsApi;
    private final BagBot bagBot;


}
