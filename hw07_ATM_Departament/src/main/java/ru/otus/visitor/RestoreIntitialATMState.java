package ru.otus.visitor;

import ru.otus.atm.ATM;

// visitor который сбрасывает показание с атм до первоначального состояния
public class RestoreIntitialATMState implements Visitor {

    @Override
    public void visit(ATM atm) {
        atm.restoreInitialATMState();
    }
}
