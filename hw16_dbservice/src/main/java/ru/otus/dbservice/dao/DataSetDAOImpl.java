package ru.otus.dbservice.dao;


import org.hibernate.Session;
import ru.otus.message_server.dataset.DataSet;
import ru.otus.message_server.dataset.UserDataS;


import java.util.List;


public class DataSetDAOImpl implements DataSetDAO {
    private final Session session;

    public DataSetDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public <T extends DataSet> T save(T dataset) {
        session.save(dataset);
        return dataset;
    }

    @Override
    public <T extends DataSet> T load(long id) {
        return (T) session.load(UserDataS.class, id);
    }

    @Override
    public <T extends DataSet> List<T> getAll() {
        List<T> list= (List<T>) session.createQuery("SELECT u FROM UserDataS u", UserDataS.class).getResultList();
        return list;
    }

}
