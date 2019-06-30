package ru.otus.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.otus.dao.UserDAO;
import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;

import java.util.function.Consumer;
import java.util.function.Function;

public class JdbcTemplateHibernate {
    private final SessionFactory sessionFactory;

    public JdbcTemplateHibernate() {
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(UserDataSet.class)
                .addAnnotatedClass(PhoneDataSet.class)
                .addAnnotatedClass(AddressDataSet.class);
        sessionFactory = createSessionFactory(configuration);

    }
    public JdbcTemplateHibernate(Configuration configuration){
        this.sessionFactory = createSessionFactory(configuration);
    }

    public static SessionFactory createSessionFactory(Configuration configuration){
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
    public void create(UserDataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            UserDAO dao = new UserDAO(session);
            dao.create(dataSet);
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
