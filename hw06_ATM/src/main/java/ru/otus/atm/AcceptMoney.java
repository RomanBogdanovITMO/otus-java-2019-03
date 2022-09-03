package ru.otus.atm;

import java.util.Map;
import java.util.logging.Logger;

public class AcceptMoney implements Operation {

    static Logger logger = Logger.getLogger(AcceptMoney.class.getName());

    @Override
    public void action(final String title, final String money,final Map<ATM.Valuta,Integer> map) {
        int count = 0;
        for (Map.Entry<ATM.Valuta,Integer> entry: map.entrySet()){
            if (entry.getKey().toString().equals(money)){
                count++;
                entry.setValue(count);
            }
        }

        logger.info(title + " " + map);
    }
}
