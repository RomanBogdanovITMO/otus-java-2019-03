package ru.otus.app.masseg;

import ru.otus.app.FrontendService;
import ru.otus.app.MsgToFrontend;
import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.Address;

public class MsgAddUserAnswer extends MsgToFrontend {
    private final UserDataSet user;
    private final int sessionId;

    public MsgAddUserAnswer(Address from, Address to, UserDataSet userDataSet, int sessionId) {
        super(from, to);
        this.sessionId = sessionId;
        this.user = userDataSet;
    }

    @Override
    public void exec(FrontendService frontendService) {
        frontendService.handleAddUserResponse(sessionId, user);
    }
}
