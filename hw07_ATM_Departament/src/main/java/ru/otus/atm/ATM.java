package ru.otus.atm;

import ru.otus.memento.ATMMemento;
import ru.otus.visitor.DepartametnElement;
import ru.otus.visitor.Visitor;

import java.util.HashMap;
import java.util.Map;

public class ATM implements DepartametnElement {
    public Map<Valuta, Integer> cell;
    private ATMMemento atmMemento;

    public enum Valuta {
        STO(),
        DVESTI(),
        PIYTSOT();

    }

    public ATM(ATM atm) {
        cell = new HashMap<>();
        for (Valuta s : Valuta.values()) {
            cell.put(s, 0);
        }
    }

    public ATM() {
        cell = new HashMap<>();
        for (Valuta s : Valuta.values()) {
            cell.put(s, 0);
        }
        atmMemento = new ATMMemento(new ATM(this));
    }

    public void start(String tittle, String countMoney, Operation operation) {

        operation.action(tittle, countMoney, cell);
    }

    public void restoreInitialATMState() {
        ATM initStateAtm = atmMemento.getSavedState();
        cell.clear();
        cell.putAll(initStateAtm.cell);
        System.out.println(cell);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
