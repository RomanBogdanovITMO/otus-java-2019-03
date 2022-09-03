package ru.otus.atm;

import java.util.Map;
import java.util.logging.Logger;

public class IssueBalance implements Operation {

    static Logger logger = Logger.getLogger(IssueBalance.class.getName());

    @Override
    public void action(final String title, final String money, final Map<ATM.Valuta, Integer> map) {

        for (Map.Entry<ATM.Valuta, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                final int value = entry.getValue();

                logger.info(entry.getKey() + ": " + "count " + value);
            }

        }

        logger.info(title + " " + map);
    }
}
