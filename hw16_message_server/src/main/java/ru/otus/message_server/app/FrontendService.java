package ru.otus.message_server.app;

import ru.otus.message_server.messageSystem.Addressee;
import ru.otus.message_server.model.UserDTO;

public interface FrontendService extends Addressee {

    void handleRequest(UserDTO userDto);

    void addUser(UserDTO userDto);


}