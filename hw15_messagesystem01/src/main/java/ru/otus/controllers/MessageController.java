package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.otus.app.FrontendService;




@Controller
public class MessageController {

    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final FrontendService frontendService;
    private final SimpMessagingTemplate template;

    public MessageController(@Lazy FrontendService frontendService, SimpMessagingTemplate template){
        this.frontendService=frontendService;
        this.template=template;
    }

    @MessageMapping("/message")
    void createUser(UserDTO userDto) throws InterruptedException {
        frontendService.handleRequest(userDto);
    }


    public void accept(UserDTO userDto) {

        logger.info("message from database: {}", userDto.getName());
        this.template.convertAndSend("/topic/response", userDto);
    }
}
