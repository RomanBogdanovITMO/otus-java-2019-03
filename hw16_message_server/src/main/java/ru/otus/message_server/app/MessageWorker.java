package ru.otus.message_server.app;

import ru.otus.message_server.annotation.Blocks;
import ru.otus.message_server.messageSystem.Message;

import java.io.IOException;

public interface MessageWorker {
    Message pool();

    void send(Message message);

    @Blocks
    Message take() throws InterruptedException;

    void close() throws IOException;
}
