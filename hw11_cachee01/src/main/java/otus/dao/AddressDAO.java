package otus.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import otus.dataset.AddressDataSet;

@AllArgsConstructor
public class AddressDAO {

    private final Session session;

    public void create(final AddressDataSet addressDataSet){
        session.save(addressDataSet);
    }
    public AddressDataSet load(final long id){
        return session.load(AddressDataSet.class,id);
    }
}
