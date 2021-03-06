package ru.otus.dataset;

import javax.persistence.*;

@Embeddable
@Entity
@Table(name = "address")
public class AddressDataSet  {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "address_street")
    private String street;

    @OneToOne(mappedBy = "address")
    private UserDataSet userDataSet;

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

    public UserDataSet getUserDataSet() {
        return userDataSet;
    }

    public void setUserDataSet(UserDataSet userDataSet) {
        this.userDataSet = userDataSet;
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                "id=" + id +
                ", street='" + street + '\'' +
                '}';
    }
}
