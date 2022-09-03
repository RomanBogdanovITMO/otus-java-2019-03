package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import ru.otus.dataset.UserDataSet;

@AllArgsConstructor
public class UserDAO {

    private final Session session;


    public void create(UserDataSet userDataSet) {
        session.save(userDataSet);
    }

    public UserDataSet load(long id) {
        return session.get(UserDataSet.class, id);
    }
}
