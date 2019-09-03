package ru.otus.message_server.messages;

import ru.otus.message_server.messageSystem.Address;
import ru.otus.message_server.messageSystem.Addressee;
import ru.otus.message_server.messageSystem.Message;
import ru.otus.message_server.messageSystem.MessageSystemContext;

public class PingMsgAnswer extends Message {
    private final MessageSystemContext context;

    public PingMsgAnswer(Address from, Address to, MessageSystemContext context) {
        super(from, to);
        this.context = context;
    }

    @Override
    public void exec(Addressee addressee) {
        addressee.setContext(context);
    }
}
