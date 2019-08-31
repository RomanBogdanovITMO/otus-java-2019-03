package ru.otus;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.dbservice.dao.UserDaoImpl;
import ru.otus.message_server.app.DBService;
import ru.otus.message_server.app.MessageWorker;
import ru.otus.message_server.channel.SocketMessageWorker;
import ru.otus.message_server.dataset.UserDataS;
import ru.otus.message_server.messageSystem.Address;
import ru.otus.message_server.messageSystem.Message;
import ru.otus.message_server.messageSystem.MessageSystemContext;
import ru.otus.message_server.messages.DbPingMsg;


import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class DBServiceImpl implements DBService {
    private static Logger logger = LoggerFactory.getLogger(DBServiceImpl.class);

    private static final String HOST = "localhost";
    private static final int MSG_SYSTEM_SERVER_PORT = 8090 ;

    private  MessageSystemContext context;

    private final Address address;

    private final SocketMessageWorker socketMessageWorker;

    private final UserDaoImpl userDao;

    private String userName;

    private String userAddress;


    public DBServiceImpl() throws IOException {
        userDao = new UserDaoImpl();
        address = new Address("dbservice");
        Socket socket = new Socket(HOST, MSG_SYSTEM_SERVER_PORT);
        socketMessageWorker = new SocketMessageWorker(socket);
        socketMessageWorker.init();
    }

    private static StandardServiceRegistry createServiceRegistry(Configuration configuration){
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return serviceRegistry;
    }

    public static void main(String[] args) throws IOException {
        new DBServiceImpl().start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            while (true) {
                try {
                    Message msg = socketMessageWorker.take(); //blocks
                    msg.exec(this);
                    logger.info("Message received from: " + msg.getFrom());
                } catch (InterruptedException e) {
                   // logger.info(Level.SEVERE, e.getMessage());
                }
            }
        });

        Message dbPingMsg = new DbPingMsg(address, null);
        socketMessageWorker.send(dbPingMsg);
    }



    @Override
    public void init() {

    }

    @Override
    public UserDataS getUser() {
        UserDataS user = new UserDataS();
        user.setName(userName);
        user.setAddress(new ru.otus.message_server.dataset.Address(userAddress));
        return userDao.save(user);
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageWorker getMsgWorker() {
        return socketMessageWorker;
    }

    @Override
    public void setContext(MessageSystemContext messageSystemContext) {
        context = messageSystemContext;
        logger.info("Set new front address: " + context.getFrontAddress());
    }

}
