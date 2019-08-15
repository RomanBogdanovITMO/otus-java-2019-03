package ru.otus.app.masseg;

import ru.otus.app.FrontendService;
import ru.otus.app.MsgToFrontend;
import ru.otus.messageSystem.Address;

public class MsgAddUserAnswer extends MsgToFrontend {
    private final String name;
    private final long sessionId;

    public MsgAddUserAnswer(Address from, Address to, long sessionId, String name) {
        super(from, to);
        this.sessionId = sessionId;
        this.name = name;
    }

    @Override
    public void exec(FrontendService frontendService) {
        frontendService.addUser(sessionId,name);
    }
}
