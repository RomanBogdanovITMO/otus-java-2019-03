package ru.otus.dbservice.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.otus.message_server.dataset.Address;
import ru.otus.message_server.dataset.DataSet;
import ru.otus.message_server.dataset.Phone;
import ru.otus.message_server.dataset.UserDataS;


import java.util.List;
import java.util.function.Function;


public class UserDaoImpl implements UserDAO {
    private SessionFactory sessionFactory;


    public UserDaoImpl() {
      /*  if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);*/
        Configuration configuration = new Configuration()
                .configure("hibernat.xml");
        Metadata metadata = new MetadataSources(createServiceRegistry(configuration))
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Phone.class)
                .addAnnotatedClass(UserDataS.class)
                .getMetadataBuilder()
                .build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }
    private static StandardServiceRegistry createServiceRegistry(Configuration configuration){
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return serviceRegistry;
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
