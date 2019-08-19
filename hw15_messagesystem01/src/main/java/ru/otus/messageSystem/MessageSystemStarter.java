package ru.otus.messageSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.otus.app.FrontendServiceImpl;

@Component("MSstarter")
public class MessageSystemStarter {

    private static Logger logger = LoggerFactory.getLogger(MessageSystemStarter.class);

    @Autowired @Qualifier("MS")
    private MessageSystem messageSystem;

    @Autowired @Qualifier("frservice")
    private FrontendServiceImpl frontendService;



    public FrontendServiceImpl getFrontendService() {
        return frontendService;
    }

    public void go(){
        frontendService.init();
        messageSystem.start();
    }


}
