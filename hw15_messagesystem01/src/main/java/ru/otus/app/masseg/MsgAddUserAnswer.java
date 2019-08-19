package ru.otus.app.masseg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.app.FrontendService;
import ru.otus.app.MsgToFrontend;
import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.Address;

public class MsgAddUserAnswer extends MsgToFrontend {
    private final UserDataSet userDataSet;
    private static Logger logger = LoggerFactory.getLogger(MsgAddUserAnswer.class);
    public MsgAddUserAnswer(Address from, Address to, UserDataSet userDataSet) {
        super(from, to);
        this.userDataSet = userDataSet;
    }



    @Override
    public void exec(FrontendService frontendService) {
        logger.info("got message:{}" + userDataSet.getName());
        frontendService.sendMessage(userDataSet);
    }
}
