package ru.otus.message_server.messages;

import ru.otus.message_server.app.FrontendService;
import ru.otus.message_server.messageSystem.Address;
import ru.otus.message_server.model.UserDTO;

public class MsgAddUserAnswer extends MsgToFrontend {
    private final UserDTO userDto;


    public MsgAddUserAnswer(Address from, Address to, UserDTO userDto) {
        super(from, to);
        this.userDto = userDto;

    }

    @Override
    public void exec(FrontendService frontendService) {

        frontendService.addUser(userDto);
    }
}
