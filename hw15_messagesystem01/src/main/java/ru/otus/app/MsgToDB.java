package ru.otus.app;

import ru.otus.messageSystem.Address;
import ru.otus.messageSystem.Addressee;
import ru.otus.messageSystem.Message;
import ru.otus.repostore.DBServiceRepositore;

public abstract class MsgToDB extends Message {
    public MsgToDB(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof DBServiceRepositore) {
            exec((DBServiceRepositore) addressee);
        }
    }
    public abstract void exec(DBServiceRepositore dbService);
}
