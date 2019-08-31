package ru.otus.message_server.messages;

import ru.otus.message_server.app.DBService;
import ru.otus.message_server.dataset.UserDataS;
import ru.otus.message_server.messageSystem.Address;
import ru.otus.message_server.model.UserDTO;

public class MsgAddUser extends MsgToDB {

    private final UserDTO userDto;


    public MsgAddUser(Address from, Address to, UserDTO userDto) {
        super(from, to);
        this.userDto =  userDto;
    }
    //Добавляем в базу и отправляем на front
    @Override
    public void exec(DBService dbService) {
        dbService.setUserName(userDto.getName());
        dbService.setUserAddress(userDto.getAddress());
        UserDataS user = dbService.getUser();
        dbService.getMsgWorker().send(new MsgAddUserAnswer(getTo(), getFrom(),userDto));

    }
}

