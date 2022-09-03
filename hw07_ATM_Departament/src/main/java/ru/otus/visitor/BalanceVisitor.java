package ru.otus.visitor;

import ru.otus.atm.ATMNew;
import ru.otus.atm.BILLS;

import java.util.Map;

//visitor который получает баланс - остаток атм
public class BalanceVisitor implements Visitor {

    @Override
    public void visit(ATMNew atm) {
        System.out.println(atm.cell);
        int count = 0;
        for (Map.Entry<BILLS, Integer> entry : atm.cell.entrySet()) {
            count += entry.getValue();
        }
        System.out.println("total balance:" + count);

    }
}
