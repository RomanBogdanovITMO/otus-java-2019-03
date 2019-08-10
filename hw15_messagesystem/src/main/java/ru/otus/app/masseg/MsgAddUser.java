package ru.otus.app.masseg;


import ru.otus.app.DBService;
import ru.otus.app.MsgToDB;
import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.Address;

public class MsgAddUser extends MsgToDB {
    private final UserDataSet user;
    private final int sessionId;

    public MsgAddUser(Address from, Address to, int sessionId, UserDataSet user) {
        super(from, to);
        this.user = user;
        this.sessionId = sessionId;

    }

    @Override
    public void exec(DBService dbService) {
        dbService.create(user);
        dbService.getMS().sendMessage(new MsgAddUserAnswer(getTo(), getFrom(), user, sessionId));
    }
}
