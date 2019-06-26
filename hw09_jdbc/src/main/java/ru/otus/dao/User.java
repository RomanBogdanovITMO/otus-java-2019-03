package ru.otus.dao;

public class User {

   private  String Name;

    private int Age;
    @MyId
    private long Id;
    public User(){

    }

    public User(long id,String name,  int age ) {
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
