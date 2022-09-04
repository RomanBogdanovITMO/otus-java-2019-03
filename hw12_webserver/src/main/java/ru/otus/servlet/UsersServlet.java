package ru.otus.servlet;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.otus.dataset.UserDataSet;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@AllArgsConstructor
public class UsersServlet extends HttpServlet {

    static Logger logger = Logger.getLogger(UsersServlet.class.getName());

    private final SessionFactory sessionFactory;
    private static final String CONTENT_TYPE_TEXT = "text/html;charset=utf-8";
    private static final String USER_PAGE_TEMPLATE = "userInfo.html";


    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        logger.info("execute method doPost() in user servlet");

        final Map<String, Object> pageVariables = createPageVariablesMap();
        resp.setContentType(CONTENT_TYPE_TEXT);
        resp.getWriter().println(TemplateProcessor.instance().getPage(USER_PAGE_TEMPLATE, pageVariables));

        resp.setStatus(HttpServletResponse.SC_OK);

    }

    private Map<String, Object> createPageVariablesMap() {
        logger.info("execute method createPageVariablesMap() in user servlet");

        final Map<String, Object> pageVariables = new HashMap<>();
        final List<UserDataSet> users = allUsers();

        if (!users.isEmpty()){
            try (Session session = sessionFactory.openSession()) {
                users.forEach(userDataSet -> {
                    UserDataSet user = session.get(UserDataSet.class, userDataSet.getId());
                    pageVariables.put("name", user.getName());
                    pageVariables.put("age", user.getAge());
                    pageVariables.put("address", user.getAddress());
                });
            }
            pageVariables.put("name", users.toString());
        }

        return pageVariables;
    }

    private List<UserDataSet> allUsers() {
        logger.info("execute method allUsers() in user servlet");

        try (Session session = sessionFactory.openSession()) {
            final CriteriaBuilder builder = session.getCriteriaBuilder();
            final CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
            criteria.from(UserDataSet.class);

            return session.createQuery(criteria).list();
        }
    }

}
