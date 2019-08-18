package ru.otus.app;

import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.Addressee;


public interface FrontendService extends Addressee {
    void init();

    void handleRequest(UserDataSet userDataSet);

    void sendMessage(UserDataSet userDataSet);


}
