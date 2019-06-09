package ru.otus.visitor;
import ru.otus.atm.ATM;

import java.util.Map;

public class BalanceVisitor implements Visitor {
    @Override
    public void visit(ATM atm) {
        System.out.println(atm.cell);
        int count = 0;
        for (Map.Entry<ATM.Valuta,Integer> entry: atm.cell.entrySet()){
            count += entry.getValue();
        }
        System.out.println("total balance:" + count);

    }
}
