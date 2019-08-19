package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.MessageSystemStarter;
import ru.otus.repostore.DBServiceRepositore;


@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired @Qualifier("MSstarter")
    private MessageSystemStarter systemStarter;
    private DBServiceRepositore dbServiceRepositore;

    public MessageController(DBServiceRepositore dbServiceRepositore) {
        this.dbServiceRepositore = dbServiceRepositore;
        dbServiceRepositore.init();
    }

    @MessageMapping("/message")
   // @SendTo("/topic/response")
    public void addUser(UserDataSet userDataSet) {
        logger.info("got message:{}" + userDataSet);
        systemStarter.getFrontendService().handleRequest(userDataSet);
    }
}
