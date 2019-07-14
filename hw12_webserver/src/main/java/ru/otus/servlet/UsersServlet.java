package ru.otus.servlet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.otus.dataset.UserDataSet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UsersServlet extends HttpServlet {
    private  SessionFactory sessionFactory;
    private static final String CONTENT_TYPE_TEXT = "text/html;charset=utf-8";
    private static final String USER_PAGE_TEMPLATE = "userInfo.html";

    public UsersServlet(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
//получить список пользователей
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap();
        resp.setContentType(CONTENT_TYPE_TEXT);
        resp.getWriter().println(TemplateProcessor.instance().getPage(USER_PAGE_TEMPLATE,pageVariables));

    }
    private Map<String, Object> createPageVariablesMap(){
        Map<String,Object> pageVariables = new HashMap<>();
        try (Session session = sessionFactory.openSession()){
            for (int i = 1; i < 4; i ++) {// как получить список id user что бы заменить этот цикл? не понятно.
                UserDataSet user1 = session.get(UserDataSet.class, i);
                pageVariables.put("name", user1.getName());
                pageVariables.put("age", user1.getAge());
                pageVariables.put("address",user1.getAddress());
            }
        }
        return pageVariables;
    }
}
