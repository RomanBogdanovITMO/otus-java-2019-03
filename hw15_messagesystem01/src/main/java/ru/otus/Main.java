package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.app.FrontendServiceImpl;
import ru.otus.app.MessageSystemContext;
import ru.otus.dataset.UserDataSet;
import ru.otus.db.DBServiceHiber;
import ru.otus.messageSystem.Address;
import ru.otus.messageSystem.MessageSystem;
@SpringBootApplication
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //запуск msg с прикручеванием DBService без сокетов (чтобы заработало надо закоментировать класс AddressConfig)
        /*MessageSystem messageSystem = new MessageSystem();
        MessageSystemContext context = new MessageSystemContext(messageSystem);

        Address frontaddress = new Address("FR");
        context.setFrontAddress(frontaddress);

        Address DBServisaddress = new Address("DB");
        context.setDbAddress(DBServisaddress);

        FrontendServiceImpl frontendService = new FrontendServiceImpl(context,frontaddress);
        frontendService.init();

        DBServiceHiber dbServiceHiber = new DBServiceHiber(context,DBServisaddress);
        dbServiceHiber.init();

        messageSystem.start();
        UserDataSet user1 = new UserDataSet("Roman");
        UserDataSet user2 = new UserDataSet("Niita");
        UserDataSet user3 = new UserDataSet("Marina");

        frontendService.handleRequest(user1);
        frontendService.handleRequest(user2);
        frontendService.handleRequest(user3);

        Thread.sleep(1000);
        messageSystem.dispose();*/

        SpringApplication.run(Main.class, args);
        System.out.println("hello");
    }
}
