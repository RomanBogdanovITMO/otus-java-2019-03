package ru.otus.servlet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserServlet extends HttpServlet {
    private SessionFactory sessionFactory;
    private static final String CONTENT_TYPE_TEXT = "text/html;charset=utf-8";
    private static final String ADD_USER_PAGE_TEMPLATE = "userADD.html";

    public AddUserServlet(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        Integer age = req.getIntHeader("age");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        UserDataSet dataSet = new UserDataSet(name,age,new AddressDataSet(address),new PhoneDataSet(phone));

        try (Session session = sessionFactory.openSession()){// java.lang.NullPointerException HTTP ERROR 500 - как исправить
            session.getTransaction();
            session.save(dataSet);
            session.getTransaction().commit();
        }

        resp.setContentType(CONTENT_TYPE_TEXT);
        resp.getWriter().println(TemplateProcessor.instance().getPageUser(ADD_USER_PAGE_TEMPLATE,dataSet));
    }

}
