package ru.otus.atm;

import ru.otus.memento.ATMMemento;
import ru.otus.visitor.DepartmentElement;
import ru.otus.visitor.Visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class ATM implements DepartmentElement {

    static Logger logger = Logger.getLogger(ATM.class.getName());

    public final Map<Valuta,Integer> cell;
    private ATMMemento atmMemento;

    public enum Valuta {
       ONE_HUNDRED(),
       TWO_HUNDRED(),
       FIVE_HUNDRED();

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

    public void start(final String tittle, final String countMoney, final Operation operation){

        operation.action(tittle, countMoney,cell);
    }

    public void restoreInitialATMState() {
        final ATM initStateAtm = atmMemento.getSavedState();
        cell.clear();
        cell.putAll(initStateAtm.cell);

        logger.info(cell.toString());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
