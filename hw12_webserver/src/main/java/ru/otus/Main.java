package ru.otus;


import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hibernate.*;
import ru.otus.db.DBServiceHiber;
import ru.otus.servlet.AddUserServlet;
import ru.otus.servlet.LoginServlet;
import ru.otus.servlet.UsersServlet;
import java.net.URL;


public class Main {
    private final static int PORT = 8080;
    private SessionFactory sessionFactory;

    public Main(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static void main(String[] args) throws Exception {
        DBServiceHiber dbServiceHiber = new DBServiceHiber();
        new Main(dbServiceHiber.getSessionFactory()).start();
    }

    private void start() throws Exception {
        Server server = createServer(PORT);
        server.start();
        server.join();
    }

    public Server createServer(int port)  {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new UsersServlet(sessionFactory)), "/userInfo");
        context.addServlet(new ServletHolder(new LoginServlet()),"/admin");
        context.addServlet(new ServletHolder(new AddUserServlet(sessionFactory)),"/userADD");
        Server server = new Server(port);
        server.setHandler(new HandlerList(context));

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{createResourceHandler(),context});
        server.setHandler(handlers);
        return server;

    }

    private ResourceHandler createResourceHandler() {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(false);
        resourceHandler.setWelcomeFiles(new String[]{"index.html"});

        URL fileDir = Main.class.getClassLoader().getResource("static");
        if (fileDir == null) {
            throw new RuntimeException("File Directory not found");
        }
        resourceHandler.setResourceBase(fileDir.getPath());
        return resourceHandler;
    }


}
