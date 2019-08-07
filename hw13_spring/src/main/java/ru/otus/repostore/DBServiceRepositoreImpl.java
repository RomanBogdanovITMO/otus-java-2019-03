package ru.otus.repostore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.dataset.UserDataSet;
import ru.otus.db.DBServiceHiber;


import java.util.List;

@Repository
public class DBServiceRepositoreImpl implements DBServiceRepositore {

    private final DBServiceHiber dbServiceHiber;

    public DBServiceRepositoreImpl(DBServiceHiber dbServiceHiber) {
        this.dbServiceHiber = dbServiceHiber;
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
    public UserDataSet load(long id) {
        UserDataSet user = this.dbServiceHiber.load(id);
        return user;
    }
}
