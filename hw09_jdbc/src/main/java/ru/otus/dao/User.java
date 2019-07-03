package ru.otus.dao;

public class User {

    @MyId
    private long Id;

    private String firstname;

    private int age;

    public User() {

    }

    public User(long id, String name, int age1) {
        firstname = name;
        age = age;
        this.Id = id;

    }

    public String getName() {
        return firstname;
    }

    public int getAge() {
        return age;
    }

    public long getId() {
        return Id;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + firstname + '\'' +
                ", Age=" + age +
                ", Id=" + Id +
                '}';
    }
}
