package ru.otus.visitor;

import ru.otus.atm.ATMNew;

// visitor который сбрасывает показание с атм до первоначального состояния
public class RestoreIntitialATMState implements Visitor {

    @Override
    public void visit(ATMNew atm) {
        atm.restoreInitialATMState();
    }
}
