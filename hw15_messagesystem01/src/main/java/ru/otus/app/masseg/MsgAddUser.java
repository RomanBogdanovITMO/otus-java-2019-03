package ru.otus.app.masseg;


import ru.otus.app.DBService;
import ru.otus.app.MsgToDB;
import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.Address;

public class MsgAddUser extends MsgToDB {
    //private final String userName;
    private UserDataSet userDataSet;

    public MsgAddUser(Address from, Address to, UserDataSet userDataSet) {
        super(from, to);
        //this.userName = userName;
        this.userDataSet = userDataSet;

    }

    @Override
    public void exec(DBService dbService) {
       // UserDataSet user = new UserDataSet(userName);
        dbService.create(userDataSet);
        dbService.getMS().sendMessage(new MsgAddUserAnswer(getTo(), getFrom(),userDataSet));
    }
}
