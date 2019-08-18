package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.MessageSystemStarter;


@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired @Qualifier("MSstarter")
    private MessageSystemStarter systemStarter;

    @MessageMapping("/message")
    @SendTo("/topic/response")
    public UserDataSet addUser(UserDataSet userDataSet) {
        logger.info("got message:{}" + userDataSet);
        systemStarter.getFrontendService().handleRequest(userDataSet);
        return new UserDataSet(HtmlUtils.htmlEscape(userDataSet.getName()));
    }
}
