package ru.otus.memento;

import ru.otus.atm.ATM;

public class ATMMemento {
    private final ATM atm;


    public ATMMemento(ATM atm) {
        this.atm = atm;
    }

    public ATM getSavedState() {
        return atm;
    }
}
