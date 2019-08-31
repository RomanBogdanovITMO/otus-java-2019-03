package ru.otus.message_server.dataset;

import javax.persistence.Entity;


@Entity
public class Address extends DataSet {

    private String street;

    public Address(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

