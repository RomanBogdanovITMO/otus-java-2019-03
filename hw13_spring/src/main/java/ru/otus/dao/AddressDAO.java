package ru.otus.dao;

import org.hibernate.Session;
import ru.otus.dataset.AddressDataSet;

public class AddressDAO {
    private Session session;

    public AddressDAO(Session session) {
        this.session = session;
    }

    public void create(AddressDataSet addressDataSet) {
        session.save(addressDataSet);
    }

    public AddressDataSet load(long id) {
        return session.load(AddressDataSet.class, id);
    }
}
