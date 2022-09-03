package ru.otus;

import ru.otus.atm.ATM;
import ru.otus.atm.AcceptMoney;
import ru.otus.atm.IssueBalance;
import ru.otus.deprtament.DepartmentATM;

import static ru.otus.util.ApplicationConstants.*;

public class ATMDemo {
    public static void main(String[] args) {
        final ATM atm = new ATM();
        final ATM atm1 = new ATM();
        final DepartmentATM departmentATM = new DepartmentATM();

        String banknote = ONE_HUNDRED;
        String banknote1 = TWO_HUNDRED;

        atm.start(COMMAND_ATM, banknote, new AcceptMoney());
        atm.start(COMMAND_ATM, banknote1, new AcceptMoney());
        atm1.start(COMMAND_ATM, banknote1, new AcceptMoney());
        atm.start("IssueBalance", banknote, new IssueBalance());


        departmentATM.addAtm(atm);
        departmentATM.addAtm(atm1);

        departmentATM.getBalance();
        departmentATM.restoreAllATM();

    }
}
