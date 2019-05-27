package ru.otus;

public class ATMDemo {
    public static void main(String[] args) {
        ATM atm = new ATM();
        int banknote = 100;
        int banknote1 = 200;
        atm.start("AcceptMoney",banknote,new AcceptMoney());
        atm.start("GiveMoney", banknote,new GiveMoney());
        atm.start("AcceptMoney",banknote1,new AcceptMoney());
        atm.start("IssueBalance",banknote,new IssueBalance());
    }
}
