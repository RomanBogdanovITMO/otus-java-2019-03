package ru.otus.app.masseg;



import ru.otus.app.MsgToDB;
import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.Address;
import ru.otus.repostore.DBServiceRepositore;

public class MsgAddUser extends MsgToDB {

    private UserDataSet userDataSet;

    public MsgAddUser(Address from, Address to, UserDataSet userDataSet) {
        super(from, to);
        this.userDataSet = userDataSet;

    }

    @Override
    public void exec(DBServiceRepositore dbService) {
        dbService.create(userDataSet);
        dbService.getMS().sendMessage(new MsgAddUserAnswer(getTo(), getFrom(),userDataSet));
    }
}
