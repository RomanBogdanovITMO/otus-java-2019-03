package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import ru.otus.dataset.DataSet;
import ru.otus.dataset.UserDataS;

import java.util.List;

@RequiredArgsConstructor
public class DataSetDAOImpl implements DataSetDAO {
    private final Session session;

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
