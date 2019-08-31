package ru.otus.message_server.messageSystem;


import ru.otus.message_server.app.MessageWorker;

/**
 * @author tully
 */
public interface Addressee {
    Address getAddress();

    MessageWorker getMsgWorker();

    void setContext(MessageSystemContext context);
}
