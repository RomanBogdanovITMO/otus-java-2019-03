package ru.otus.bot;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
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

    public BagBot(QuestionAndAnswerRepository repository) {
        this.repository = repository;

    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        List<QuestionAndAnswer> listsQuestionAndA = repository.findAll();
        int valueList = listsQuestionAndA.size();
        int count = 0;


        if (message != null && message.hasText()) {
            String textUser = message.getText();
            for (QuestionAndAnswer lists : listsQuestionAndA) {
                logger.info("id question: " + lists.getId());
                count++;
                if (lists.getQuestion().equals(textUser)) {
                    requestOfUserForBag(lists, textUser, message);
                    sendMSG(message, getParserString(lists.getAnswer()));
                    logger.info("server response: " + lists.getAnswer());
                    break;
                } else if (count == valueList) {
                    sendMSG(message, "извините, данная команда в разработке или запрос не корректный");
                }
            }
        } else if (update.hasCallbackQuery()) {
            try {
                execute(new SendMessage().setText(
                        update.getCallbackQuery().getData())
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }

    //обрабатывается запрос клиента если найдено совпадение , выгружаем список фото данному запросу
    private void requestOfUserForBag(QuestionAndAnswer questionAnd, String textUser, Message message) {
        String[] nameComands = {"7","8","9","7б","8б","9б","7ч","8ч","9ч","7ц","8ц","9ц","10"};
        List<String> stringList = Arrays.asList(nameComands);

        for (String list: stringList){
            if (list.equals(textUser)){
                getListBag(message,questionAnd);
                break;
            }else if (("/показать все".equals(textUser)) || (list.equals(textUser))){
                getListBag(message,questionAnd);
                break;
            }
        }

    }

    //получаем список фотографий из dir-static на основе запроса клиента
    private void getListBag(Message message, QuestionAndAnswer questionAndAnswer) {

        String nameDirectory = questionAndAnswer.getAdditionInfo();
        File myfile = new File("C:\\otus-java-2019-03- 01\\telegramBot\\src\\main\\resources\\static\\" + nameDirectory);
        File[] files = myfile.listFiles();
        List<File> listFiles = Arrays.asList(files);

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId().toString());
        sendPhoto.setReplyToMessageId(message.getMessageId());

        for (File list : listFiles) {
            try {
                sendPhoto(sendPhoto.setNewPhoto(list));
                execute(setButtons1(message.getChatId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    //создаем новую строку(ответ) взятую из бд(ответ) и возвращаем ответ для клиента
    private String getParserString(String text) {
        String resaultParserString = "";
        for (String str : text.split("#")) {
            String value = str.trim();
            resaultParserString += value + "\n";
        }
        return resaultParserString;
    }

    //отправка сообщения клиенту
    private void sendMSG(Message message, String text) {

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

    //формируем клавиатуру с кнопками(клавиатура формируется сразу же при нажатии команды /start
    private void setButtons(SendMessage sendMessage) {
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
    //формируем клавиатуру с кнопкой(клавиатура не постоянного типа, формируется при просмотре сумок )
    private SendMessage setButtons1(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("показат");
        inlineKeyboardButton1.setCallbackData("Button \"Тык\" has been pressed");


        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId).setText("Описание сумки").setReplyMarkup(inlineKeyboardMarkup);
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
