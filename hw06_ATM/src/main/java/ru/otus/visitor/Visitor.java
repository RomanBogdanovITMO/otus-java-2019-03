package ru.otus.visitor;


import ru.otus.atm.ATM;

public interface Visitor {
    void visit(ATM atm);
}
