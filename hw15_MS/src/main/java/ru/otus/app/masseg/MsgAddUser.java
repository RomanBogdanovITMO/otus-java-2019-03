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
        UserDataS user = dbService.getUser(userDto);
        dbService.getMS().sendMessage(new MsgAddUserAnswer(getTo(), getFrom(),userDto));
        // ваш комментарий" Отправка должна быть снаружи сообщения." Но в лекции по MS  от
        //Vitaly Kutsenko именно так делается ----
       /* @Override
        public void exec(DBService dbService) {
            int id = dbService.getUserId(login);
            dbService.getMS().sendMessage(new MsgGetUserIdAnswer(getTo(), getFrom(), login, id));
        }*/
        // или я что то не понимаю?
    }
}
