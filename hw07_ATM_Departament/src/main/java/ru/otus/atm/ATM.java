package ru.otus.atm;

import ru.otus.memento.ATMMemento;
import ru.otus.visitor.DepartametnElement;
import ru.otus.visitor.Visitor;

import java.util.HashMap;
import java.util.Map;

public class ATM implements DepartametnElement {
    public Map<BILLS, Integer> cell;
    private ATMMemento atmMemento;

//конструктор который создает атм с пустыми ячецками (для записи в мементо) паттерн мементо
 hw09_jdbc
    public ATM(ATM atm) {
        cell = new HashMap<>();
        for (BILLS s : BILLS.values()) {
            cell.put(s, 0);
        }

public ATM(ATM atm) {
    cell = new HashMap<>();
    for (BILLS s : BILLS.values()) {
        cell.put(s, 0);
 hw01-maven
    }
}
    //в месте с обьектом формируется ячейки с валютами и и обьект мементо .
    public ATM() {
        cell = new HashMap<>();
        for (BILLS s : BILLS.values()) {
            cell.put(s, 0);
        }
        atmMemento = new ATMMemento(new ATM(this));
    }
 hw09_jdbc
//метод для вызова нужных операций "AcceptMoney, IssueBalance, GiveMoney" используется полиморфизм

    //метод для вызова нужных операций "AcceptMoney, IssueBalance, GiveMoney" используется полиморфизм
 hw01-maven
    public void start(String tittle, int countMoney, Operation operation) {

        operation.action(tittle, countMoney, cell);
    }
    // метод восстанавливает состояние ATM до начального
    public void restoreInitialATMState() {
        ATM initStateAtm = atmMemento.getSavedState();
        System.out.println("first " + cell);
        cell.clear();
        cell.putAll(initStateAtm.cell);
        System.out.println(cell);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
