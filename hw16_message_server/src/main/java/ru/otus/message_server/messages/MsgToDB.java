package ru.otus.message_server.messages;

import ru.otus.message_server.app.DBService;
import ru.otus.message_server.messageSystem.Address;
import ru.otus.message_server.messageSystem.Addressee;
import ru.otus.message_server.messageSystem.Message;

public abstract class MsgToDB extends Message {
    public MsgToDB(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof DBService) {
            exec((DBService) addressee);
        }
    }

    public abstract void exec(DBService dbService);
}