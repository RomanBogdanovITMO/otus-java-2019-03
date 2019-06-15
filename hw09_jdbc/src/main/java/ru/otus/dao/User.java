package ru.otus.dao;

public class User {

    private final String Name;

    private final int Age;
    @id
    private final long Id;

    public User(String name, int age, long id) {
        Name = name;
        Age = age;
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public int getAge() {
        return Age;
    }

    public long getId() {
        return Id;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", Age=" + Age +
                ", Id=" + Id +
                '}';
    }
}
