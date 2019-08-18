package ru.otus.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.otus.app.DBService;
import ru.otus.app.MessageSystemContext;
import ru.otus.dao.UserDAO;
import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.Address;
import ru.otus.messageSystem.Addressee;
import ru.otus.messageSystem.MessageSystem;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component("dbservice")
public class DBServiceHiber implements DBService,Addressee {
    private final SessionFactory sessionFactory;

    @Autowired @Qualifier("dbAddress")
    private  Address address;

    private final MessageSystemContext context;



    public DBServiceHiber(MessageSystemContext context, Address address) {
        this.context = context;
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
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public void create(UserDataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(dataSet);
            session.getTransaction().commit();
        }
    }

    @Override
    public UserDataSet load(long id) {
        return runInSession(session -> {
            UserDAO dao = new UserDAO(session);
            return dao.load(id);
        });
    }

    @Override
    public UserDataSet readByName(String name) {
        return runInSession(session -> {
            UserDAO dao = new UserDAO(session);
            return dao.readByName(name);
        });
    }

    @Override
    public List<UserDataSet> allUsers() {
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserDataSet> critarial = builder.createQuery(UserDataSet.class);
            critarial.from(UserDataSet.class);
            return session.createQuery(critarial).list();
        }
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

    @Override
    public Address getAddress() {
        return this.address;
    }

    @Override
    public MessageSystem getMS() {
        return this.context.getMessageSystem();
    }
}
