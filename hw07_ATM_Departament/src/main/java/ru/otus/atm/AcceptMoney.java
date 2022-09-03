package ru.otus.atm;

import java.util.Map;
import java.util.logging.Logger;

// принимать банкноты разных номиналов (полиморфизм)
public class AcceptMoney implements Operation {

    static Logger logger = Logger.getLogger(AcceptMoney.class.getName());

    @Override
    public void action(final String title, final int money, final Map<BILLS, Integer> map) {
        int countMoneyInIssue = 0;
        int countMoneyBefore = 0;
        int countMoneyAfter = 0;

        for (Map.Entry<BILLS, Integer> entry : map.entrySet()) {
            countMoneyBefore += entry.getValue();
        }
        for (Map.Entry<BILLS, Integer> entry : map.entrySet()) {
            if (entry.getKey().getValue() == money) {
                countMoneyInIssue++;
                entry.setValue(countMoneyInIssue);
            }
            countMoneyAfter += entry.getValue();
        }
        if (countMoneyBefore == countMoneyAfter) {
            logger.info("May be you have not correct BILLS: " + money);
        }

        logger.info(title + " " + map);
    }
}
