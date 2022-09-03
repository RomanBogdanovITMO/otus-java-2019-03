package ru.otus.visitor;

import ru.otus.atm.ATM;

public class RestoreInitialATMState implements Visitor {

    @Override
    public void visit(final ATM atm) {
        atm.restoreInitialATMState();
    }
}
