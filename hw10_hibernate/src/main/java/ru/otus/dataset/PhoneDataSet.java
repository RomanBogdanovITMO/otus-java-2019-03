package ru.otus.dataset;

import org.hibernate.annotations.Parent;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.util.Objects;
@Embeddable
@Entity
@Table(name = "phone")
public class PhoneDataSet  {
    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "phone_number", nullable = false)
    private String number;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDataSet user;

    public PhoneDataSet() {
    }

    public PhoneDataSet(String number) {
        this.number = number;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDataSet getUser() {
        return user;
    }

    public void setUse(UserDataSet user) {
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

   /* public void setNumber(String number) {
        this.number = number;
    }*/

    @Override
    public String toString() {
        return "PhoneDataSet{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneDataSet phone = (PhoneDataSet) o;
        return Objects.equals(id, phone.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
