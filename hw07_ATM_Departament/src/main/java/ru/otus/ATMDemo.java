package ru.otus;

import ru.otus.atm.ATM;
import ru.otus.atm.AcceptMoney;
import ru.otus.atm.IssueBalance;
import ru.otus.deprtament.DepartamentATM;

public class ATMDemo {
    public static void main(String[] args) {
        ATM atm = new ATM();
        ATM atm1 = new ATM();
        DepartamentATM departamentATM = new DepartamentATM();

        String banknote = "BILLS_STO";
        String banknote1 = "BILLS_DVESTI";

        atm.start("AcceptMoney", banknote, new AcceptMoney());
        departamentATM.addAtm(atm);
        departamentATM.restoreAllATM();
        System.out.println("_____________");
        atm.start("AcceptMoney", banknote1, new AcceptMoney());

        atm1.start("AcceptMoney", banknote1, new AcceptMoney());


        atm.start("IssueBalance", banknote, new IssueBalance());


       // departamentATM.addAtm(atm);
        departamentATM.addAtm(atm1);

        departamentATM.getBalance();
        departamentATM.restoreAllATM();

    }
}
