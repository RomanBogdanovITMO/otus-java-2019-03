package ru.otus.app;


import ru.otus.ms.messageSystem.Address;
import ru.otus.ms.messageSystem.Addressee;
import ru.otus.ms.messageSystem.Message;

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

