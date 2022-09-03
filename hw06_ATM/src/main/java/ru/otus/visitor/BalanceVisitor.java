package ru.otus.visitor;
import ru.otus.atm.ATM;

import java.util.Map;
import java.util.logging.Logger;

public class BalanceVisitor implements Visitor {

    static Logger logger = Logger.getLogger(BalanceVisitor.class.getName());

    @Override
    public void visit(ATM atm) {
        logger.info(atm.cell.toString());

        int count = 0;
        for (Map.Entry<ATM.Valuta,Integer> entry: atm.cell.entrySet()){
            count += entry.getValue();
        }

        logger.info("total balance:" + count);

    }
}
