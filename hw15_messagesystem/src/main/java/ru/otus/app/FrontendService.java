package ru.otus.app;

import ru.otus.messageSystem.Addressee;


public interface FrontendService extends Addressee {
    void init();
    void handleRequest(String login);

    void addUser(long id, String name);


}
