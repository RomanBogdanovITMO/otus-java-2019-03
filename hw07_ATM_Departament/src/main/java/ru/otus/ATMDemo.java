package ru.otus;

import ru.otus.atm.ATMNew;
import ru.otus.atm.AcceptMoney;
import ru.otus.atm.GiveMoney;
import ru.otus.atm.IssueBalance;
import ru.otus.deprtament.DepartmentATM;

public class ATMDemo {
    public static void main(String[] args) {
        final ATMNew atm = new ATMNew();
        final ATMNew atm1 = new ATMNew();
        DepartmentATM departmentATM = new DepartmentATM();

        int banknote = 100;
        int banknote1 = 200;

        atm.start("AcceptMoney", banknote, new AcceptMoney());
        departmentATM.addAtm(atm);
        departmentATM.restoreAllATM();
        System.out.println("_____________");
        atm.start("AcceptMoney", banknote1, new AcceptMoney());

        atm1.start("AcceptMoney", banknote1, new AcceptMoney());


        atm.start("IssueBalance", banknote, new IssueBalance());


        // departmentATM.addAtm(atm);
        departmentATM.addAtm(atm1);

        departmentATM.getBalance();
        departmentATM.restoreAllATM();

    }
}
