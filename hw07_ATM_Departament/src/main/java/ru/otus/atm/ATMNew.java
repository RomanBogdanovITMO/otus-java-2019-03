package ru.otus.atm;

import ru.otus.memento.ATMMemento;
import ru.otus.visitor.DepartametnElement;
import ru.otus.visitor.Visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ATMNew implements DepartametnElement {

    static Logger logger = Logger.getLogger(AcceptMoney.class.getName());

    public final Map<BILLS, Integer> cell;
    private ATMMemento atmMemento;

    //конструктор который создает атм с пустыми ячецками (для записи в мементо) паттерн мементо
    public ATMNew(ATMNew atm) {
        cell = new HashMap<>();
        for (BILLS s : BILLS.values()) {
            cell.put(s, 0);
        }
    }

    //в месте с обьектом формируется ячейки с валютами и и обьект мементо .
    public ATMNew() {
        cell = new HashMap<>();
        for (BILLS s : BILLS.values()) {
            cell.put(s, 0);
        }
        atmMemento = new ATMMemento(new ATMNew(this));
    }

    //метод для вызова нужных операций "AcceptMoney, IssueBalance, GiveMoney" используется полиморфизм
    public void start(final String tittle, final int countMoney, final Operation operation) {

        operation.action(tittle, countMoney, cell);
    }

    // метод восстанавливает состояние ATM до начального
    public void restoreInitialATMState() {
        final ATMNew initStateAtm = atmMemento.getSavedState();
        logger.info("first " + cell);

        cell.clear();
        cell.putAll(initStateAtm.cell);
        logger.info(cell.toString());
    }

    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }
}
