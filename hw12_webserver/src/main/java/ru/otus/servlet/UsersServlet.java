package ru.otus.servlet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.otus.dataset.UserDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsersServlet extends HttpServlet {
    private  SessionFactory sessionFactory;

    public UsersServlet(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resultAsString = "<p>userInfo Page</p>";
        List<UserDataSet> listUsers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            listUsers.add(session.get(UserDataSet.class,1));
        }
    }
}
