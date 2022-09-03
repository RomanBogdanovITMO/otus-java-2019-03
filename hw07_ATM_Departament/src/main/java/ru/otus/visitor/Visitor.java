package ru.otus.visitor;


import ru.otus.atm.ATMNew;

// сам visitor для атм
public interface Visitor {

    void visit(final ATMNew atm);
}
