package ru.otus.app;

import ru.otus.controllers.UserDTO;
import ru.otus.ms.messageSystem.Addressee;


public interface FrontendService extends Addressee {
    void init();

    void handleRequest(UserDTO userDto);

    void addUser(UserDTO userDto);


}
