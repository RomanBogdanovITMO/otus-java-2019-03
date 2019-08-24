package ru.otus.app;

import ru.otus.controllers.UserDTO;
import ru.otus.dataset.UserDataS;
import ru.otus.ms.messageSystem.Addressee;


public interface DBService extends Addressee {
    void init();

    UserDataS getUser(UserDTO userDto);
}
