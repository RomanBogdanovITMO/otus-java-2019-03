package ru.otus.ms.front;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import ru.otus.app.FrontendService;
import ru.otus.app.MessageSystemContext;
import ru.otus.app.masseg.MsgAddUser;
import ru.otus.controllers.MessageController;
import ru.otus.controllers.UserDTO;
import ru.otus.ms.messageSystem.Address;
import ru.otus.ms.messageSystem.Message;
import ru.otus.ms.messageSystem.MessageSystem;

import javax.annotation.PostConstruct;


@Service
@DependsOn("ms")
public class FrontendServiceImpl implements FrontendService {

    private final MessageSystemContext context;

    private  final Address address;

    private final MessageController controller;




    public FrontendServiceImpl( MessageSystemContext context, @Qualifier("Frontend") Address address,  MessageController controller){
        this.context=context;
        this.address=address;
        this.controller=controller;
    }


    @PostConstruct
    public void init() {

        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    public void handleRequest(UserDTO userDto) {
        Message message = new MsgAddUser(getAddress(), context.getDbAddress(), userDto);
        context.getMessageSystem().sendMessage(message);
    }

    public void addUser(UserDTO userDto) {
        controller.accept(userDto);
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }



}
