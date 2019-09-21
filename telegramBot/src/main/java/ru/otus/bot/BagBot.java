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

import ru.otus.model.QuestionAndAnswer;
import ru.otus.repository.QuestionAndAnswerRepository;
import ru.otus.util.PropertiesHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@Component
public class BagBot extends TelegramLongPollingBot {
    private final static Logger logger = Logger.getLogger(BagBot.class.getName());
    private final static String BOT_USER_NAME;
    private final static String TOKEN;


    private QuestionAndAnswerRepository repository;
    static {
        Properties properties = PropertiesHelper.getProperties("telegrambot.properties");
        BOT_USER_NAME = properties.getProperty("telegrambot.botUserName");
        TOKEN = properties.getProperty("telegrambot.token");

    }
    public BagBot(QuestionAndAnswerRepository repository){
        this.repository = repository;

    }
    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId().toString());
        sendPhoto.setReplyToMessageId(message.getMessageId());


        List<QuestionAndAnswer> listsQuestionAndA = repository.findAll();
        int valueList = listsQuestionAndA.size();
        int count = 0;

        if (message != null && message.hasText()) {
            String textUser = message.getText();
            logger.info("message user request: " + "id " + message.getChatId() + ": " + textUser);
            for (QuestionAndAnswer lists: listsQuestionAndA){
                count++;
                if (lists.getQuestion().equals(textUser)) {
                    requestOfUserForBag(lists,textUser,message);
                    sendMSG(message, getParserString(lists.getAnswer()));
                    logger.info("server response: " + lists.getAnswer());
                    break;
                }else if (count == valueList){
                    sendMSG(message, "извините, данная команда в разработке или запрос не корректный");
                }
            }

        }
    }
//обрабатывается запрос клиента если найдено совпадение , выгружаем список фото данному запросу
    private void requestOfUserForBag(QuestionAndAnswer questionAnd, String textUser, Message message){

        if ("/большая".equals(textUser)){
            getListBag(message,questionAnd);
        }else if ("/средняя".equals(textUser)){
            getListBag(message,questionAnd);
        }else if ("/маленькая".equals(textUser)){
            getListBag(message,questionAnd);
        }else if ("/показать все".equals(textUser)){
            getListBag(message,questionAnd);
        }

    }

    //получаем список фотографий из dir-static на основе запроса клиента
    private void getListBag(Message message,QuestionAndAnswer questionAndAnswer ){

        String nameDirectory = questionAndAnswer.getAdditionInfo();
        File myfile = new File("C:\\otus-java-2019-03- 01\\telegramBot\\src\\main\\resources\\static\\" + nameDirectory);
        File[] files = myfile.listFiles();
        List<File>listFiles = Arrays.asList(files);

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId().toString());
        sendPhoto.setReplyToMessageId(message.getMessageId());

        for (File list: listFiles){
            try {
                sendPhoto(sendPhoto.setNewPhoto(list));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    //создаем новую строку(ответ) взятую из бд(ответ) и возвращаем ответ для клиента
    private String getParserString(String text){
        String resaultParserString = "";
        for (String str: text.split("-")){
            resaultParserString += str + "\n";
        }
        return resaultParserString;
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

        keyboardFirstRow.add(new KeyboardButton("/start"));
        keyboardFirstRow.add(new KeyboardButton("/выбрать сумку"));
        keyboardSeconfRow.add(new KeyboardButton("/помощь"));
        keyboardSeconfRow.add(new KeyboardButton("/показать все"));

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
