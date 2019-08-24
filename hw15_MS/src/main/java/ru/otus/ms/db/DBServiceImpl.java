package ru.otus.ms.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import ru.otus.app.DBService;
import ru.otus.app.MessageSystemContext;
import ru.otus.controllers.UserDTO;
import ru.otus.dao.UserDaoImpl;
import ru.otus.dataset.UserDataS;
import ru.otus.ms.messageSystem.Address;
import ru.otus.ms.messageSystem.MessageSystem;

import javax.annotation.PostConstruct;

@Service
@DependsOn("ms")
public class DBServiceImpl implements DBService {


    private static Logger logger = LoggerFactory.getLogger(DBServiceImpl.class);

    private final MessageSystemContext context;


    private final Address address;

    private final UserDaoImpl userDao;

    public DBServiceImpl(MessageSystemContext context, @Qualifier("DB") Address address, UserDaoImpl userDao){
        this.context=context;
        this.address=address;
        this.userDao=userDao;
    }

    @PostConstruct
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }

    //вы предлогали сделать UserDataS getUser(String userName)-- а как мне тогда добавить адрес для user?
    //user.setAddress(new ru.otus.dataset.Address(userDto.getAddress()));
    public UserDataS getUser(UserDTO userDto) {
        UserDataS user = new UserDataS();
        user.setName(userDto.getName());
        user.setAddress(new ru.otus.dataset.Address(userDto.getAddress()));
        return userDao.save(user);
    }
}
