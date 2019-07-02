package ru.otus.dataset;


import org.hibernate.annotations.Parent;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserDataSet  {
    @Id
    @GeneratedValue
    private long Id;

    private String Name;

    private int Age;

    @Embedded
    @Target(PhoneDataSet.class)
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PhoneDataSet> phoneDataSetList = new ArrayList<>();

    @Embedded
    @Target(AddressDataSet.class)
    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    public UserDataSet() {

    }

    public UserDataSet(String name, int age,AddressDataSet addressDataSet,PhoneDataSet phone) {
        this.Name = name;
        this.Age = age;
        this.address = addressDataSet;
        phoneDataSetList.add(phone);
        phone.setUse(this);
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    public  PhoneDataSet getPhone(){
        return phoneDataSetList.get(0);
    }

    public void addPhone(PhoneDataSet phone) {
        phoneDataSetList.add(phone);
        phone.setUse(this);
    }

    public void removePhone(PhoneDataSet phone) {
        phoneDataSetList.remove(phone);
        phone.setUse(null);
    }

    public AddressDataSet getAddress() {
        return address;
    }

    public void setAddress(AddressDataSet address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Age=" + Age +
                ", phoneDataSetList=" + phoneDataSetList +
                ", address=" + address +
                '}';
    }
}
