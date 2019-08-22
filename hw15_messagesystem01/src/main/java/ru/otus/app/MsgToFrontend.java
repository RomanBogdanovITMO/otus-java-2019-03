package ru.otus.app;


import ru.otus.ms.messageSystem.Address;
import ru.otus.ms.messageSystem.Addressee;
import ru.otus.ms.messageSystem.Message;

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
