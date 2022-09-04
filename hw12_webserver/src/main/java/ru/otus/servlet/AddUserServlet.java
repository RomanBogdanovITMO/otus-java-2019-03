package ru.otus.servlet;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class AddUserServlet extends HttpServlet {

    private final SessionFactory sessionFactory;
    private static final String CONTENT_TYPE_TEXT = "text/html;charset=utf-8";
    private static final String ADD_USER_PAGE_TEMPLATE = "userADD.html";


    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {

        doPost(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final PhoneDataSet phoneDataSet = PhoneDataSet.builder()
                .number(req.getParameter("phone"))
                .build();
        final AddressDataSet addressDataSet = AddressDataSet.builder()
                .street(req.getParameter("address"))
                .build();
        final UserDataSet userDataSet = UserDataSet.builder()
                .name(req.getParameter("name"))
                .age(req.getIntHeader("age"))
                .build();
        phoneDataSet.setUser(userDataSet);
        addressDataSet.setUserDataSet(userDataSet);
        userDataSet.setAddress(addressDataSet);


        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(userDataSet);
            session.getTransaction().commit();
        }
        resp.setContentType(CONTENT_TYPE_TEXT);
        resp.getWriter().println(TemplateProcessor.instance().getPageUser(ADD_USER_PAGE_TEMPLATE, userDataSet));
    }
}
