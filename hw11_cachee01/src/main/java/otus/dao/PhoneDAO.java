package otus.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import otus.dataset.PhoneDataSet;

@AllArgsConstructor
public class PhoneDAO {

    private final Session session;

    public void create(final PhoneDataSet phoneDataSet) {
        session.save(phoneDataSet);
    }

    public PhoneDataSet load(final long id) {
        return session.load(PhoneDataSet.class, id);
    }
}
