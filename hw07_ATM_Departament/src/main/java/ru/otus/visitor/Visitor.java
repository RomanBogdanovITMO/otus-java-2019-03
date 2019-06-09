package ru.otus.visitor;


import ru.otus.atm.ATM;
// сам visitor для атм
public interface Visitor {
    void visit(ATM atm);
}
