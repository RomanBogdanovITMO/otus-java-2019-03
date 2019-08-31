package ru.otus.message_server.messageSystem;

import ru.otus.message_server.annotation.Blocks;
import ru.otus.message_server.app.MessageWorker;
import ru.otus.message_server.channel.SocketMessageWorker;
import ru.otus.message_server.messages.DbPingMsg;
import ru.otus.message_server.messages.FrontPingMsg;
import ru.otus.message_server.messages.PingMsgAnswer;
import ru.otus.message_server.util.PropertiesHelper;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class MessageSystem implements MessageSystemMBean {
    private final static Logger logger = Logger.getLogger(MessageSystem.class.getName());

     private static final int PORT = 8090;
    private static final int THREADS_NUMBER = 1;
    private static final int STEP_TIME = 100;

   /* private static final int PORT;
    private static final int THREADS_NUMBER;
    private static final int STEP_TIME;
    static {
        Properties properties = PropertiesHelper.getProperties("msgsystem.properties");
        PORT = Integer.parseInt(properties.getProperty("msgsystem.port"));
        THREADS_NUMBER = Integer.parseInt(properties.getProperty("msgsystem.threads"));
        STEP_TIME = Integer.parseInt(properties.getProperty("msgsystem.step"));
    }*/

    private final ExecutorService executor;
    private final List<MessageWorker> workers;
    private final Map<Address, MessageWorker> clients;
    private final MessageSystemContext context;

    public MessageSystem() {
        executor = Executors.newFixedThreadPool(THREADS_NUMBER);
        workers = new CopyOnWriteArrayList<>();
        clients = new ConcurrentHashMap<>();
        context = new MessageSystemContext();
    }

    @Blocks
    public void start() throws Exception {
        executor.submit(this::processMessage);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info("Server started on port: " + serverSocket.getLocalPort());
            while (!executor.isShutdown()) {
                Socket socket = serverSocket.accept(); //blocks
                SocketMessageWorker worker = new SocketMessageWorker(socket);
                worker.init();
                worker.addShutdownRegistration(() -> {
                    workers.remove(worker);
                    clients.values().remove(worker);
                });
                workers.add(worker);
            }
        }
    }
    @SuppressWarnings("InfiniteLoopStatement")
    private void processMessage() {
        while (true) {
            for (MessageWorker worker : workers) {
                Message msg = worker.pool();
                while (msg != null) {
                    if (msg.getClass().isAssignableFrom(FrontPingMsg.class)) {
                        Address sender = msg.getFrom();
                        logger.info("Ping message received from: " + sender);
                        context.setFrontAddress(sender);
                        clients.put(sender, worker);

                        sendCurrentContextToAllAddresses();
                    } else if (msg.getClass().isAssignableFrom(DbPingMsg.class)) {
                        Address sender = msg.getFrom();
                        logger.info("Ping message received from: " + sender);
                        context.setDbAddress(sender);
                        clients.put(sender, worker);

                        sendCurrentContextToAllAddresses();
                    } else {
                        Address destination = msg.getTo();
                        logger.info("Transit message to: " + destination);
                        MessageWorker msgWorker = clients.get(destination);
                        if (msgWorker != null) {
                            msgWorker.send(msg);
                        }
                    }
                    msg = worker.pool();
                }
            }
            try {
                Thread.sleep(STEP_TIME);
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, e.getMessage());

            }
        }
    }

    private void sendCurrentContextToAllAddresses() {
        for (Map.Entry<Address, MessageWorker> entry : clients.entrySet()) {
            MessageWorker worker = entry.getValue();
            worker.send(new PingMsgAnswer(null, entry.getKey(), context));
        }
    }
    @Override
    public boolean getRunning() {
        return true;
    }

    @Override
    public void setRunning(boolean running) {
        if (!running) {
            executor.shutdown();
            logger.info("Bye.");
        }
    }
}