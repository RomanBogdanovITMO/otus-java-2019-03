package ru.otus.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.otus.messageSystem.Address;

@Configuration
public class AddressConfig {

    @Bean("frontAddress")
    @Primary
    public Address frontAddress() {
        return new Address("frontAddress");
    }
    @Bean("dbAddress")
    public Address  dbAddress() {
        return new Address("dbAddress");
    }
}
