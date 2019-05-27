package ru.otus.visitor;

import ru.otus.atm.ATM;

public class RestoreIntitialATMState implements Visitor {

    @Override
    public void visit(ATM atm) {
        atm.restoreInitialATMState();
    }
}
