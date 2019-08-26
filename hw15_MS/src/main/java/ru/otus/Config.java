package ru.otus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.otus.app.MessageSystemContext;
import ru.otus.ms.messageSystem.Address;
import ru.otus.ms.messageSystem.MessageSystem;

@Configuration
public class Config {
    @Bean(name = "ms")
    MessageSystem getMessageSystem() {
        MessageSystem ms = new MessageSystem();
        return ms;
    }

    @Bean(name = "Frontend")
    @Primary
    Address getFEAddress() {
        return new Address("Frontend");
    }

    @Bean(name = "DB")
    Address getDBAddress() {
        return new Address("DB");
    }

    @Bean
    MessageSystemContext getMessageSystemContext() {
        MessageSystemContext context = new MessageSystemContext(getMessageSystem());

        context.setFrontAddress(getFEAddress());

        context.setDbAddress(getDBAddress());
        return context;

    }
}
