package ru.otus.app.masseg;



import ru.otus.app.DBService;
import ru.otus.app.MsgToDB;
import ru.otus.controllers.UserDTO;
import ru.otus.dataset.UserDataS;
import ru.otus.ms.messageSystem.Address;


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
        dbService.getMS().sendMessage(new MsgAddUserAnswer(getTo(), getFrom(),userDto));

    }
}
