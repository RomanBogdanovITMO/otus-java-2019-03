package ru.otus.message_server.messages;

import ru.otus.message_server.messageSystem.Address;
import ru.otus.message_server.messageSystem.Addressee;
import ru.otus.message_server.messageSystem.Message;

public class FrontPingMsg extends Message {

    public FrontPingMsg(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
    }
}
