package ru.otus.visitor;

import ru.otus.atm.ATM;
import ru.otus.atm.Bills;

import java.util.Map;
//visitor который получает баланс - остаток атм
public class BalanceVisitor implements Visitor {
    @Override
    public void visit(ATM atm) {
        System.out.println(atm.cell);
        int count = 0;
        for (Map.Entry<Bills, Integer> entry : atm.cell.entrySet()) {
            count += entry.getValue();
        }
        System.out.println("total balance:" + count);

    }
}
