package ru.otus.message_server.messages;

import ru.otus.message_server.messageSystem.Addressee;
import ru.otus.message_server.app.FrontendService;
import ru.otus.message_server.messageSystem.Address;
import ru.otus.message_server.messageSystem.Message;

public abstract class MsgToFrontend extends Message {
    public MsgToFrontend(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof FrontendService) {
            exec((FrontendService) addressee);
        } else {
            //todo error!
        }
    }
    public abstract void exec(FrontendService frontendService);
}
