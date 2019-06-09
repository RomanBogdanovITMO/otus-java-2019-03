package ru.otus.deprtament;

import ru.otus.atm.ATM;
import ru.otus.visitor.BalanceVisitor;
import ru.otus.visitor.RestoreIntitialATMState;

import java.util.ArrayList;
import java.util.List;

public class DepartamentATM {
    private List<ATM> atms = new ArrayList<>();

    public void addAtm(ATM atm) {
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
