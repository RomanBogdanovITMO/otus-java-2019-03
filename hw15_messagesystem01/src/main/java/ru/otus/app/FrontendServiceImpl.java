package ru.otus.app;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ru.otus.app.masseg.MsgAddUser;
import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.Address;
import ru.otus.messageSystem.Message;
import ru.otus.messageSystem.MessageSystem;

import java.util.logging.Logger;

@Component("frservice")
public class FrontendServiceImpl implements FrontendService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FrontendServiceImpl.class);

    private  Address address;
    @Autowired
    private SimpMessagingTemplate messageForRender;

    private final MessageSystemContext context;


    private static Logger log = Logger.getLogger("Frontend");

    public FrontendServiceImpl(MessageSystemContext context, @Autowired @Qualifier("frontAddress")Address address) {
        this.context = context;
        this.address = address;

    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }


    @Override
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public void handleRequest(UserDataSet userDataSet) {
        Message message = new MsgAddUser(getAddress(),context.getDbAddress(),userDataSet);
        context.getMessageSystem().sendMessage(message);
    }

    @Override
    public void sendMessage(UserDataSet userDataSet) {
        logger.info("got message:{}" + userDataSet.getName());
        messageForRender.convertAndSend("/topic/response",userDataSet);

    }

}
