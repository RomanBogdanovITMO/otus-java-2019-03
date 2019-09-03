package ru.otus.message_server.messageSystem;

public interface MessageSystemMBean {
    boolean getRunning();

    void setRunning(boolean running);
}
