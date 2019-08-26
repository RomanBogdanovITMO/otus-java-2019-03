package ru.otus.app.masseg;


import ru.otus.app.FrontendService;
import ru.otus.app.MsgToFrontend;
import ru.otus.controllers.UserDTO;
import ru.otus.ms.messageSystem.Address;


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
