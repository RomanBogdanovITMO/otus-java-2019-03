package ru.otus.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.dataset.DataSet;
import ru.otus.dataset.UserDataS;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Function;

@Repository
public class UserDaoImpl implements UserDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }


    @Override
    public <T extends DataSet> List<T> getAll() {
        return runInSession(session -> {
            DataSetDAOImpl dao = new DataSetDAOImpl(session);
            List<UserDataS> list = dao.getAll();
            Hibernate.initialize(list);
            return (List<T>) list;
        });
    }

    public <T extends DataSet> T save(T dataSet) {
        return runInSession(session -> {
            DataSetDAOImpl dao = new DataSetDAOImpl(session);
            return dao.save(dataSet);
        });
    }


    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }
    public <T extends DataSet> T load(long id) {
        return runInSession(session -> {
            DataSetDAOImpl dao = new DataSetDAOImpl(session);
            UserDataS object = dao.load(id);
            Hibernate.initialize(object);
            return (T) object;
        });
    }
}
