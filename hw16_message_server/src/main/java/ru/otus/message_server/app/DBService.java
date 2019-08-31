package ru.otus.message_server.app;

import ru.otus.message_server.dataset.UserDataS;
import ru.otus.message_server.messageSystem.Addressee;

public interface DBService extends Addressee {
    void init();

    UserDataS getUser();


    void setUserName(String userName);


    void setUserAddress(String userAddress);

}