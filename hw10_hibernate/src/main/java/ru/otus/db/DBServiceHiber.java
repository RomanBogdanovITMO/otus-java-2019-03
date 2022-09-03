package ru.otus.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ru.otus.Main;
import ru.otus.dao.UserDAO;
import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;

public class DBServiceHiber {

    static Logger logger = Logger.getLogger(Main.class.getName());
    private final SessionFactory sessionFactory;

    public DBServiceHiber() {
        final Configuration configuration = new Configuration()
                .configure("hibernate.xml");
        final Metadata metadata = new MetadataSources(createServiceRegistry(configuration))
                .addAnnotatedClass(UserDataSet.class)
                .addAnnotatedClass(PhoneDataSet.class)
                .addAnnotatedClass(AddressDataSet.class)
                .getMetadataBuilder()
                .build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();

    }

    private static StandardServiceRegistry createServiceRegistry(final Configuration configuration) {
        logger.info("execute method createServiceRegistry: ");

        return new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
    }

    public void create(final UserDataSet dataSet) {
        logger.info("execute method create: ");

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(dataSet);
            session.getTransaction().commit();
        }
    }

    public UserDataSet load(final long id) {
        logger.info("execute method load id: " + id);

        return runInSession(session -> {
            final UserDAO dao = new UserDAO(session);
            return dao.load(id);
        });
    }

    public String getLocalStatus() {
        logger.info("execute method getLocalStatus: ");

        return runInSession(session -> {
            return session.getTransaction().getStatus().name();
        });
    }

    private void runInSession(final Consumer<Session> consumer) {
        logger.info("execute method runInSession: ");

        try (Session session = sessionFactory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            consumer.accept(session);
            transaction.commit();
        }
    }

    private <R> R runInSession(final Function<Session, R> function) {
        logger.info("execute method runInSession: ");

        try (Session session = sessionFactory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            final R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }
}
