package ru.otus.app.masseg;

import ru.otus.app.FrontendService;
import ru.otus.app.MsgToFrontend;
import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.Address;

public class MsgAddUserAnswer extends MsgToFrontend {
    private final UserDataSet userDataSet;

    public MsgAddUserAnswer(Address from, Address to, UserDataSet userDataSet) {
        super(from, to);
        this.userDataSet = userDataSet;
    }



    @Override
    public void exec(FrontendService frontendService) {
        frontendService.sendMessage(userDataSet);
    }
}
