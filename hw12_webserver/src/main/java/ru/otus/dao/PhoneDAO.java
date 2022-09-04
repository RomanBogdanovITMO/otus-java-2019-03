package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import ru.otus.dataset.PhoneDataSet;

@AllArgsConstructor
public class PhoneDAO {

    private final Session session;


    public void create(PhoneDataSet phoneDataSet) {
        session.save(phoneDataSet);
    }

    public PhoneDataSet load(long id) {
        return session.load(PhoneDataSet.class, id);
    }
}
