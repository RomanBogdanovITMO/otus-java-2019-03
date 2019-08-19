package ru.otus.repostore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.otus.app.MessageSystemContext;
import ru.otus.dataset.UserDataSet;
import ru.otus.db.DBServiceHiber;
import ru.otus.messageSystem.Address;
import ru.otus.messageSystem.MessageSystem;

import java.util.List;

@Repository
public class DBServiceRepositoreImpl implements DBServiceRepositore {

    private final DBServiceHiber dbServiceHiber;
    @Autowired
    @Qualifier("dbAddress")
    private Address address;

    private final MessageSystemContext context;


    public DBServiceRepositoreImpl(DBServiceHiber dbServiceHiber,MessageSystemContext context, Address address) {
        this.dbServiceHiber = dbServiceHiber;
        this.context = context;
        this.address = address;
    }

    @Override
    public void create(UserDataSet dataSet) {
        this.dbServiceHiber.create(dataSet);

    }

    @Override
    public List<UserDataSet> allUsers() {
        return this.dbServiceHiber.allUsers();

    }

    @Override
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public UserDataSet load(long id) {
        UserDataSet user = this.dbServiceHiber.load(id);
        return user;
    }


    @Override
    public Address getAddress() {
        return this.address;
    }

    @Override
    public MessageSystem getMS() {
        return this.context.getMessageSystem();
    }
}
