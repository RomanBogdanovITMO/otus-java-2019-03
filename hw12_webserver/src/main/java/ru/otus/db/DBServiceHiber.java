package ru.otus.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.dao.UserDAO;
import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;

import java.util.function.Consumer;
import java.util.function.Function;

public class DBServiceHiber {
    private final SessionFactory sessionFactory;

    public DBServiceHiber() {
        Configuration configuration = new Configuration()
                .configure("hibernate.xml");
        Metadata metadata = new MetadataSources(createServiceRegistry(configuration))
                .addAnnotatedClass(UserDataSet.class)
                .addAnnotatedClass(PhoneDataSet.class)
                .addAnnotatedClass(AddressDataSet.class)
                .getMetadataBuilder()
                .build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static StandardServiceRegistry createServiceRegistry(Configuration configuration){
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return serviceRegistry;
    }

    public void create(UserDataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(dataSet);
            session.getTransaction().commit();
        }
    }

    public UserDataSet load(long id) {
        return runInSession(session -> {
            UserDAO dao = new UserDAO(session);
            return dao.load(id);
        });
    }
    public String getLocalStatus() {
        return runInSession(session -> {
            return session.getTransaction().getStatus().name();
        });
    }
    private void runInSession(Consumer<Session> consumer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            consumer.accept(session);
            transaction.commit();
        }
    }
    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }
}
