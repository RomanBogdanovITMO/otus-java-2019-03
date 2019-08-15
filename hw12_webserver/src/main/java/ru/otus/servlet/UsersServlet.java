package ru.otus.servlet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.otus.dataset.UserDataSet;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersServlet extends HttpServlet {
    private  SessionFactory sessionFactory;
    private static final String CONTENT_TYPE_TEXT = "text/html;charset=utf-8";
    private static final String USER_PAGE_TEMPLATE = "userInfo.html";

    public UsersServlet(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

   /* @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap();
        resp.setContentType(CONTENT_TYPE_TEXT);
        resp.getWriter().println(TemplateProcessor.instance().getPage(USER_PAGE_TEMPLATE,pageVariables));
 

    }*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap();
        resp.setContentType(CONTENT_TYPE_TEXT);
        resp.getWriter().println(TemplateProcessor.instance().getPage(USER_PAGE_TEMPLATE,pageVariables));

        resp.setStatus(HttpServletResponse.SC_OK);
 
    }

    private Map<String, Object> createPageVariablesMap(){
        Map<String,Object> pageVariables = new HashMap<>();
        List<UserDataSet> userlists = allUsers();

        try (Session session = sessionFactory.openSession()){
            for (UserDataSet list: userlists){
                UserDataSet user1 = session.get(UserDataSet.class, list.getId());
                pageVariables.put("name", user1.getName());
                pageVariables.put("age", user1.getAge());
                pageVariables.put("address",user1.getAddress());
            }
        }
        pageVariables.put("name", userlists.toString());
        //  pageVariables.put("age", userlists.size());
        //  pageVariables.put("address",userlists.size());
        // pageVariables.put("name", userlists.size());


        return pageVariables;
    }
    private List<UserDataSet> allUsers(){
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserDataSet> critarial = builder.createQuery(UserDataSet.class);
            critarial.from(UserDataSet.class);
            return session.createQuery(critarial).list();
        }
    }

}
