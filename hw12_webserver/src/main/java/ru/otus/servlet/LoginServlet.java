package ru.otus.servlet;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    private static final String ADMIN_AUTHORIZED = "adminAuthorized";
    private static final String LOGIN_PAGE_TEMPLATE = "login.html";
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "pazzword";
    private static final String AUTHORIZED = "Authorized";
    private static final String CONTENT_TYPE = "text/html;charset=utf-8";

    private final TemplateProcessor templateProcessor;


    @SuppressWarnings("WeakerAccess")
    public LoginServlet(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

    @SuppressWarnings("WeakerAccess")
    public LoginServlet() throws IOException {
        this(new TemplateProcessor());
    }

    private String getPage(boolean adminAuthorized) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(ADMIN_AUTHORIZED, adminAuthorized);
        return templateProcessor.getPage(LOGIN_PAGE_TEMPLATE, pageVariables);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(AUTHORIZED) == null) {
            session.setAttribute(AUTHORIZED, false);
        }

        String requestLogin = request.getParameter(LOGIN);
        String requestPassword = request.getParameter(PASSWORD);

        if (requestLogin != null && requestPassword != null) {
            if (requestLogin.equals(ADMIN_LOGIN) && requestPassword.equals(ADMIN_PASSWORD)) {
                authorizeAdmin(request);
            } else {
                deAuthorizeAdmin(request);
            }
        }

        setOK(response);
        boolean adminAuthorized = (boolean) session.getAttribute(AUTHORIZED);
        String page = getPage(adminAuthorized); //save to the page
        response.getWriter().println(page);
    }


    private void authorizeAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute(AUTHORIZED, true);
    }

    private void deAuthorizeAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute(AUTHORIZED, false);
    }

    private void setOK(HttpServletResponse response) {
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
