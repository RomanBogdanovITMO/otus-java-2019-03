package ru.otus.app.masseg;

import ru.otus.app.FrontendService;
import ru.otus.app.MsgToFrontend;
import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.Address;

public class MsgGetUserInfoAnswer extends MsgToFrontend {
    private final long userId;
    private final UserDataSet user;
    private final int sessionId;

    public MsgGetUserInfoAnswer(Address from, Address to, int sessionId, long userId, UserDataSet user) {
        super(from, to);
        this.userId = userId;
        this.user = user;
        this.sessionId = sessionId;
    }

    @Override
    public void exec(FrontendService frontendService) {
        frontendService.handleGetUserInfoResponse(sessionId, user);
    }
}
