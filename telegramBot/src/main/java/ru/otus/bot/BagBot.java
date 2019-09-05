package ru.otus.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class BagBot extends TelegramLongPollingBot {
    private final String BOT_USER_NAME = "SumkiRomanBot";
    private final String TOKEN = "861995581:AAH7sqrDvmZKj6TSFB7LoLUjtzlbvhPSTKE";

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId().toString());
        sendPhoto.setReplyToMessageId(message.getMessageId());
        sendPhoto.setNewPhoto(new File("../telegramBot/src/main/resources/static/bag.png"));

        if (message != null && message.hasText()) {
            String s = message.getText();
            if ("/help".equals(s)) {
                sendMSG(message, "чем могу помочь?");

            } else if ("/setting".equals(s)) {
                sendMSG(message, "что будем выбирать");

            }else if ("/start".equals(s)){
                sendMSG(message,"Привет, меня зовут Bagbot");
            }
            else {
                sendMSG(message,"Сумки");
                try {
                    sendPhoto(sendPhoto);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//отправка сообщения клиенту
    private void sendMSG(Message message,String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
//формируем клавиатуру с кнопками
    private void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowlist = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSeconfRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/setting"));
        keyboardSeconfRow.add(new KeyboardButton("/bag"));

        keyboardRowlist.add(keyboardFirstRow);
        keyboardRowlist.add(keyboardSeconfRow);

        replyKeyboardMarkup.setKeyboard(keyboardRowlist);
    }

    @Override
    public String getBotUsername() {
        return BOT_USER_NAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
