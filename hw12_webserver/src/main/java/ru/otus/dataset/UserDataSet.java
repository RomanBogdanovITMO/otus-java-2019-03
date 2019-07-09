package ru.otus.dataset;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDR_ADDR_ID")
    private AddressDataSet address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private List<PhoneDataSet> phones = new ArrayList<>();

    public UserDataSet() {
    }

    public UserDataSet(UserDataSet userDataSet) {
        this.name = userDataSet.getName();
        this.age = userDataSet.getAge();
        this.address = userDataSet.getAddress();
        this.phones = userDataSet.getPhones();
    }


    public UserDataSet(String name, Integer age, AddressDataSet address, List<PhoneDataSet> phones) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public AddressDataSet getAddress() {
        return address;
    }

    public void setAddress(AddressDataSet address) {
        this.address = address;
    }

    public List<PhoneDataSet> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDataSet> phones) {
        this.phones.addAll(phones);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDataSet)) return false;
        UserDataSet that = (UserDataSet) o;
        List<PhoneDataSet> phonesThat = that.getPhones();
        if (phones.size() != phonesThat.size()) {
            return false;
        }
        boolean checkPhones = true;
        for (int i = 0; i < phones.size(); i++) {
            if (!phones.get(i).equals(phonesThat.get(i))) checkPhones = false;
        }
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAge(), that.getAge()) &&
                Objects.equals(getAddress(), that.getAddress()) && checkPhones;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getAddress(), getPhones());
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", phones=" + phones +
                '}';
    }
}

