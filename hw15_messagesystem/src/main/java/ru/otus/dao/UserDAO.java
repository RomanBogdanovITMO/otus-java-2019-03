package ru.otus.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.otus.dataset.UserDataSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserDAO {
    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }
    public void create(UserDataSet userDataSet){
        session.save(userDataSet);
    }

    public UserDataSet load(long id){
        return session.get(UserDataSet.class,id);
    }

    public UserDataSet readByName(String name) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
        Root<UserDataSet> from = criteria.from(UserDataSet.class);
        criteria.where(builder.equal(from.get("name"), name));
        Query<UserDataSet> query = session.createQuery(criteria);
        return query.uniqueResult();
    }
}
