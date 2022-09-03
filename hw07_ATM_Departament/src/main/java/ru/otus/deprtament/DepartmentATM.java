package ru.otus.deprtament;

import ru.otus.atm.ATMNew;
import ru.otus.visitor.BalanceVisitor;
import ru.otus.visitor.RestoreIntitialATMState;

import java.util.ArrayList;
import java.util.List;

public class DepartmentATM {
    private final List<ATMNew> atms = new ArrayList<>();

    public void addAtm(ATMNew atm) {
        atms.add(atm);
    }

    //Департамент может собирать сумму остатков со всех ATM (visitor)
    public void getBalance() {
        BalanceVisitor balanceVisitor = new BalanceVisitor();
        atms.forEach(a -> a.accept(balanceVisitor));
    }

    //Департамент может инициировать событие – восстановить состояние всех ATM до начального(visitor)
    public void restoreAllATM() {
        RestoreIntitialATMState restoreIntitialATMState = new RestoreIntitialATMState();
        atms.forEach(a -> a.accept(restoreIntitialATMState));
    }
}
