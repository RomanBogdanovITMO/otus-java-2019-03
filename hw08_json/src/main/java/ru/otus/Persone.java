package ru.otus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Persone {
    private String firstName;
    private String lastName;
    private int age;
    private boolean childrens = true;
    List<String> phoneNumbers = new ArrayList<String>(Arrays.asList("1234","911","03","02"));
    String[]cars = {"lada","porche","bmw"};

    public Persone(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
