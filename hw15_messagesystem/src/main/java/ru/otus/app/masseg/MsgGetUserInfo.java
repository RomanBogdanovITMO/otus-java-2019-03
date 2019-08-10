package ru.otus.app.masseg;

import ru.otus.app.DBService;
import ru.otus.app.MsgToDB;
import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.Address;

public class MsgGetUserInfo extends MsgToDB {

    private final long userId;
    private final int sessionId;

    public MsgGetUserInfo(Address from, Address to, int sessionId, long userId) {
        super(from, to);
        this.userId = userId;
        this.sessionId = sessionId;
    }

    @Override
    public void exec(DBService dbService) {
        UserDataSet user = null;
        try {
            user = dbService.load(userId);
        } catch (Exception e){
            e.printStackTrace();
        }
        dbService.getMS().sendMessage(new MsgGetUserInfoAnswer(getTo(), getFrom(), sessionId, userId, user));
    }
}
