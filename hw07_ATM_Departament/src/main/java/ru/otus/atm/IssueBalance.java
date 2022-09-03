package ru.otus.atm;

import java.util.Map;
import java.util.logging.Logger;

// выдавать сумму остатка денежных средств (полиморфизм)
public class IssueBalance implements Operation {

    static Logger logger = Logger.getLogger(AcceptMoney.class.getName());

    @Override
    public void action(final String title, final int money, final Map<BILLS, Integer> map) {

        for (Map.Entry<BILLS, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                final int value = entry.getValue();
                logger.info(entry.getKey() + ": " + "count " + value);
            }
        }
        logger.info(title + " " + map);
    }
}
