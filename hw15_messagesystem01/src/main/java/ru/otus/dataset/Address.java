package ru.otus.dataset;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Address extends DataSet {

    public Address(String street) {
        this.street = street;
    }

    private String street;

}

