package ru.otus.atm;

import java.util.Map;
import java.util.logging.Logger;

// выдавать запрошенную сумму (полиморфизм)
public class GiveMoney implements Operation {

    static Logger logger = Logger.getLogger(AcceptMoney.class.getName());

    @Override
    public void action(final String title, final int money, final Map<BILLS, Integer> map) {
        int count = 0;
        boolean flag = false;
        for (Map.Entry<BILLS, Integer> entry : map.entrySet()) {
            if (entry.getKey().getValue() == money) {
                count++;
                logger.info(title + " " + entry.getKey());

                final int countMapBancknot = entry.getValue() - count;
                entry.setValue(countMapBancknot);
                flag = true;
            }
        }
        if (!flag) {

            logger.info("ATM is empty");
        }

        logger.info("ATM " + map);
    }
}
