package ru.otus.dataset;

//import lombok.Data;

import javax.persistence.*;
import java.util.Set;

//@Data
@Entity
@Table(name = "user")
public class UserDataS extends DataSet {
    private String name;

    private int age;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @OneToMany(mappedBy = "userDataSet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Phone> phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Phone> getPhone() {
        return phone;
    }

    public void setPhone(Set<Phone> phone) {
        this.phone = phone;
    }
}
