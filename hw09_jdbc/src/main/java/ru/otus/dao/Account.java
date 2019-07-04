package ru.otus.dao;

public class Account {
    @MyId
    private long id;
    private String nameAccount;
    private int valueAccount;

    public Account() {
    }

    public Account(String nameAccount1,long id, int valueAccount1) {
        this.nameAccount = nameAccount1;
        this.id = id;
        this.valueAccount = valueAccount1;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public int getValueAccount() {
        return valueAccount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", nameAccount='" + nameAccount + '\'' +
                ", valueAccount=" + valueAccount +
                '}';
    }
}
