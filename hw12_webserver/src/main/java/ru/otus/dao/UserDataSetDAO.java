package ru.otus.dao;

import org.hibernate.Session;
import ru.otus.dataset.UserDataSet;

public class UserDataSetDAO {
    private Session session;

    public UserDataSetDAO(Session session) {
        this.session = session;
    }

    public void create(UserDataSet dataSet) {
        session.save(dataSet);
    }

    public UserDataSet load(long id) {
        return session.get(UserDataSet.class, id);
    }

}
