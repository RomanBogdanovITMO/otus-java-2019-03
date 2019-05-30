package ru.otus.atm;

import java.util.Map;
// выдавать запрошенную сумму (полиморфизм)
public class GiveMoney implements Operation {
    @Override
    public void action(String title, String money, Map<Bills,Integer> map) {
        int count = 0;
        for (Map.Entry<Bills,Integer> entry: map.entrySet()){
            if (entry.getKey().toString().equals(money)){
                count++;
                System.out.println(title + " " + entry.getKey());
                int countMapBancknot = entry.getValue() - count;
                entry.setValue(countMapBancknot);

            }
        }
        System.out.println("ATM " + map);
    }
}
