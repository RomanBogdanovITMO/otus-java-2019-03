package ru.otus.atm;

import java.util.Map;

public class AcceptMoney implements Operation {
    @Override
    public void action(String title, String money,Map<ATM.Valuta,Integer> map) {
        int count = 0;
        for (Map.Entry<ATM.Valuta,Integer> entry: map.entrySet()){
            if (entry.getKey().toString().equals(money)){
                count++;
                entry.setValue(count);
            }
        }

        System.out.println(title + " " + map);
    }
}
