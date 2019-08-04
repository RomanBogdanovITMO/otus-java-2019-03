package ru.otus.dao;

import org.hibernate.Session;
import ru.otus.dataset.UserDataSet;

public class UserDAO {
    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }
    public void create(UserDataSet userDataSet){
        session.save(userDataSet);
    }

    public UserDataSet load(long id){
        return session.get(UserDataSet.class,id);
    }
}
