package ru.otus.dao;

import org.hibernate.Session;
        import ru.otus.dataset.PhoneDataSet;

public class PhoneDAO {
    private Session session;

    public PhoneDAO(Session session) {
        this.session = session;
    }

    public void create(PhoneDataSet phoneDataSet){
        session.save(phoneDataSet);
    }
    public PhoneDataSet load(long id){
        return session.load(PhoneDataSet.class,id);
    }
}
