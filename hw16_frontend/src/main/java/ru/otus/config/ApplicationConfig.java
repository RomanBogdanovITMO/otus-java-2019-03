package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.message_server.channel.SocketMessageWorker;
import ru.otus.message_server.messageSystem.Address;

import java.io.IOException;
import java.net.Socket;

@Configuration
public class ApplicationConfig {
    @Value("${messageServer.host}")
    private String host;
    @Value("${messageServer.port}")
    private int port;

    @Bean("worker")
    public SocketMessageWorker socketMsgWorker() throws IOException {
        Socket socket = new Socket(host, port);
        return new SocketMessageWorker(socket);
    }

    @Bean("front")
    public Address frontAddress() {
        return new Address("front");
    }
}

