package ru.otus.app;

import org.springframework.web.bind.annotation.RestController;
import ru.otus.app.masseg.MsgAddUser;
import ru.otus.messageSystem.Address;
import ru.otus.messageSystem.Message;
import ru.otus.messageSystem.MessageSystem;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class FrontendServiceImpl implements FrontendService {
    private final Address address;
    private final MessageSystemContext context;


    private static Logger log = Logger.getLogger("Frontend");

    private final Map<Integer, String> users = new HashMap<>();


    public FrontendServiceImpl(MessageSystemContext context, Address address) {
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
    public void handleRequest(String userName) {
        Message message = new MsgAddUser(getAddress(),context.getDbAddress(),userName);
        context.getMessageSystem().sendMessage(message);
    }

    @Override
    public void addUser(long id, String name) {
//Нужно отправить обратно сообщение с именем usera по  websocket (верно?)
    }
}
