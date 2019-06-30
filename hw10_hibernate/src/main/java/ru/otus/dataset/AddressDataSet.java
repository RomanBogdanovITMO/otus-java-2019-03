package ru.otus.dataset;

import javax.persistence.*;


@Entity
@Table(name = "address")
public class AddressDataSet  {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "address_street", nullable = false)
    private String street;



    public AddressDataSet() {
    }

    public AddressDataSet(String street) {
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }


    @Override
    public String toString() {
        return "AddressDataSet{" +
                ", street='" + street + '\'' +
                '}';
    }

}
