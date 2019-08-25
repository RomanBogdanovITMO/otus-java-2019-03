package ru.otus.dataset;

//import lombok.Data;

import javax.persistence.Entity;

//@Data
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

