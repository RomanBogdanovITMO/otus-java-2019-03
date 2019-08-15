package ru.otus.app.masseg;


import ru.otus.app.DBService;
import ru.otus.app.MsgToDB;
import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.Address;

public class MsgAddUser extends MsgToDB {
    private final String userName;

    public MsgAddUser(Address from, Address to, String userName) {
        super(from, to);
        this.userName = userName;

    }

    @Override
    public void exec(DBService dbService) {
        UserDataSet user = new UserDataSet(userName);
        dbService.create(user);
        dbService.getMS().sendMessage(new MsgAddUserAnswer(getTo(), getFrom(),user.getId(),user.getName()));
    }
}
