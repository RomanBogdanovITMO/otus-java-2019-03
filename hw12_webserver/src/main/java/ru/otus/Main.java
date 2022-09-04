package ru.otus;


import lombok.AllArgsConstructor;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.SecurityHandler;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;
import org.hibernate.*;
import ru.otus.db.DBServiceHiber;
import ru.otus.servlet.AddUserServlet;
import ru.otus.servlet.UsersServlet;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Objects;

@AllArgsConstructor
public class Main {

    private final static int PORT = 8080;
    private final SessionFactory sessionFactory;


    public static void main(String[] args) throws Exception {
        final DBServiceHiber dbServiceHiber = new DBServiceHiber();
        new Main(dbServiceHiber.getSessionFactory()).start();
    }

    private void start() throws Exception {
        final Server server = createServer();
        server.start();
        server.join();
    }

    private Server createServer() throws MalformedURLException {
        final ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new UsersServlet(sessionFactory)), "/userInfo");
        context.addServlet(new ServletHolder(new AddUserServlet(sessionFactory)),"/userADD");
        final Server server = new Server(Main.PORT);
        server.setHandler(new HandlerList(context));

        final HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{createResourceHandler(),createSecurityHandler(context)});
        server.setHandler(handlers);
        return server;

    }

    private ResourceHandler createResourceHandler() {
        final ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(false);
        resourceHandler.setWelcomeFiles(new String[]{"index.html"});

        final URL fileDir = Main.class.getClassLoader().getResource("static");
        if (Objects.isNull(fileDir)) {
            throw new RuntimeException("File Directory not found");
        }
        resourceHandler.setResourceBase(fileDir.getPath());
        return resourceHandler;
    }

    private SecurityHandler createSecurityHandler(final ServletContextHandler context) throws MalformedURLException {
        final Constraint constraint = new Constraint();
        constraint.setName("auth");
        constraint.setAuthenticate(true);
        constraint.setRoles(new String[]{"user", "admin"});

        final ConstraintMapping mapping = new ConstraintMapping();
        mapping.setPathSpec("/userADD/*");
        mapping.setConstraint(constraint);

        final ConstraintSecurityHandler security = new ConstraintSecurityHandler();
        security.setAuthenticator(new BasicAuthenticator());

        URL propFile = null;
        final File realmFile = new File("./src/main/resources/cfg/real.properties");
        if (realmFile.exists()) {
            propFile = realmFile.toURI().toURL();
        }
        if (Objects.isNull(propFile)) {
            propFile = Main.class.getClassLoader().getResource("real.properties");
        }

        if (Objects.isNull(propFile)) {
            throw new RuntimeException("Realm property file not found");
        }

        security.setLoginService(new HashLoginService("MyRealm", propFile.getPath()));
        security.setHandler(new HandlerList(context));
        security.setConstraintMappings(Collections.singletonList(mapping));

        return security;
    }


}
