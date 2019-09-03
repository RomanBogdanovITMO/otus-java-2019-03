package ru.otus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.otus.message_server.app.FrontendService;
import ru.otus.message_server.app.MessageWorker;
import ru.otus.message_server.channel.SocketMessageWorker;
import ru.otus.message_server.messageSystem.Address;
import ru.otus.message_server.messageSystem.Message;
import ru.otus.message_server.messageSystem.MessageSystemContext;
import ru.otus.message_server.messages.FrontPingMsg;
import ru.otus.message_server.messages.MsgAddUser;
import ru.otus.message_server.model.UserDTO;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class MessageController implements FrontendService {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private MessageSystemContext context;

    @Autowired
    @Qualifier("front")
    private Address address;

    @Autowired @Qualifier("worker")
    private SocketMessageWorker socketMsgWorker;

    @Autowired
    private SimpMessagingTemplate messageForRender;


    @PostConstruct
    @SuppressWarnings("InfiniteLoopStatement")
    public void init() {
        socketMsgWorker.init();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            while (true) {
                try {
                    Message msg = socketMsgWorker.take();
                    msg.exec(this);
                    logger.info("Message received from: " + msg.getFrom());
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
            }
        });

        Message msg = new FrontPingMsg(address, null);
        socketMsgWorker.send(msg);
    }

    @MessageMapping("/message")
    public void handleRequest(UserDTO userDto) {
        Message message = new MsgAddUser(getAddress(), context.getDbAddress(), userDto);
        socketMsgWorker.send(message);
    }

    @Override
    public void addUser(UserDTO userDto) {
        logger.info("message from database: {}", userDto.getName());
        messageForRender.convertAndSend("/topic/response", userDto);
    }


    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageWorker getMsgWorker() {
        return socketMsgWorker;
    }

    @Override
    public void setContext(MessageSystemContext messageSystemContext) {
        context = messageSystemContext;
    }
}
