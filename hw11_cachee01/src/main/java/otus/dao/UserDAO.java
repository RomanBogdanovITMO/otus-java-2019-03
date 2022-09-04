package otus.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import otus.dataset.UserDataSet;

@AllArgsConstructor
public class UserDAO {

    private final Session session;

    public void create(final UserDataSet userDataSet){
        session.save(userDataSet);
    }

    public UserDataSet load(final long id){
        return session.get(UserDataSet.class,id);
    }
}
