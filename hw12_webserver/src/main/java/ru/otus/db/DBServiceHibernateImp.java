package ru.otus.db;

import ru.otus.dataset.UserDataSet;

public class DBServiceHibernateImp implements DBService {

    public DBServiceHibernateImp() {
    }

    @Override
    public String getLocalStatus() {
        DBServiceHiber hiber = new DBServiceHiber();
        return hiber.getLocalStatus();
    }

    @Override
    public void create(UserDataSet dataSet) {
        DBServiceHiber hiber = new DBServiceHiber();
        hiber.create(dataSet);
    }

    @Override
    public UserDataSet load(long id) {
        DBServiceHiber hiber = new DBServiceHiber();
        hiber.load(id);
        return null;
    }

    @Override
    public void shutdown() {
        DBServiceHiber hiber = new DBServiceHiber();
        hiber.shutdown();
    }
}
