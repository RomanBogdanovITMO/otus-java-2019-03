package ru.otus.app;

import ru.otus.dataset.UserDataS;
import ru.otus.ms.messageSystem.Addressee;


public interface DBService extends Addressee {
    void init();

    UserDataS getUser();


    void setUserName(String userName);


    void setUserAddress(String userAddress);

}
