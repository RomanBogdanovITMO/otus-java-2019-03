package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import ru.otus.dataset.AddressDataSet;


@AllArgsConstructor
public class AddressDAO {
    private final Session session;

    public void create(AddressDataSet addressDataSet){
        session.save(addressDataSet);
    }
    public AddressDataSet load(long id){
        return session.load(AddressDataSet.class,id);
    }
}
