package ru.otus;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.cache.CacheEngine;
import ru.otus.cache.CacheEngineImpl;
import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;
import ru.otus.db.DBService;
import ru.otus.db.DBServiceCachedImpl;
import ru.otus.servlet.AdminServlet;
import ru.otus.servlet.LoginServlet;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public_html";

    public static void main(String[] args) throws Exception {
        //DBService
        CacheEngine<Long,UserDataSet> cache = new CacheEngineImpl<>(5);
        DBService dbService = new DBServiceCachedImpl();

        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);

        AddressDataSet address1 = new AddressDataSet("Lomonosova");
        List<PhoneDataSet> nikPhonesList = new ArrayList<>();
        PhoneDataSet phoneNum1 = new PhoneDataSet("7 705 555 34 87");
        PhoneDataSet phoneNum2 = new PhoneDataSet("7 703 454 54 23");
        nikPhonesList.add(phoneNum1);
        nikPhonesList.add(phoneNum2);

        UserDataSet user1 = new UserDataSet();
        user1.setName("Nik");
        user1.setAge(20);
        user1.setAddress(address1);
        user1.setPhones(nikPhonesList);

        System.out.println(user1);

        dbService.create(user1);

        AddressDataSet address2 = new AddressDataSet("Nevcky prospect");
        List<PhoneDataSet> pomanPhonesList = new ArrayList<>();
        PhoneDataSet phone1 = new PhoneDataSet("7 707 124 55 67");
        PhoneDataSet phone2 = new PhoneDataSet("7 708 124 54 69");
        pomanPhonesList.add(phone1);
        pomanPhonesList.add(phone2);

        UserDataSet user2 = new UserDataSet("Poman", 25, address2, pomanPhonesList);

        dbService.create(user2);

        UserDataSet dataSet = dbService.load(user1.getId());
        System.out.println(dataSet);

        //WEB Server

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new AdminServlet(cache)), "/admin");
        context.addServlet(LoginServlet.class, "/login");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();

        dbService.shutdown();
    }
}
