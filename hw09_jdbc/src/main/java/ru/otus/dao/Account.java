package ru.otus.dao;

public class Account {
    @MyId
    private long id;
    private String NameAccount;
    private int ValueAccount;

    public Account() {
    }

    public Account(String nameAccount,long id, int valueAccount) {
        NameAccount = nameAccount;
        this.id = id;
        ValueAccount = valueAccount;
    }

    public String getNameAccount() {
        return NameAccount;
    }

    public int getValueAccount() {
        return ValueAccount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", NameAccount='" + NameAccount + '\'' +
                ", ValueAccount=" + ValueAccount +
                '}';
    }
}
