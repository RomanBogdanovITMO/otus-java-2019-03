package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.otus.app.FrontendServiceImpl;
import ru.otus.app.masseg.MsgAddUser;
import ru.otus.app.masseg.MsgAddUserAnswer;
import ru.otus.db.DBServiceHiber;



@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private FrontendServiceImpl front;
    private DBServiceHiber serviceHiber;

    public MessageController(FrontendServiceImpl front, DBServiceHiber serviceHiber) {
        this.front = front;
        this.serviceHiber = serviceHiber;
    }

    @MessageMapping("/message")
    @SendTo("/topic/response")
    public MsgAddUser getMessage(MsgAddUser message) {
        front.init();// нужно ли вызывать данный метод ?
        front.handleRequest(message.getUserName());// нужно ли вызывать данный метод ?
        logger.info("got message:{}" + message);
        return new MsgAddUser(front.getAddress(),serviceHiber.getAddress(),message.getUserName());
    }
//возвращаю сообщение обратно на фронт (верно?)
    @SendTo("/topic/response")
    public MsgAddUserAnswer getMessage(MsgAddUserAnswer message) {
        logger.info("got message:{}" + message);
        return new MsgAddUserAnswer(serviceHiber.getAddress(),front.getAddress(),message.getSessionId(),message.getName());//
    }

}
