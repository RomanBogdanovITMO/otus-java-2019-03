package ru.otus.atm;

import java.util.Map;
// выдавать сумму остатка денежных средств (полиморфизм)
public class IssueBalance implements Operation {
    @Override
    public void action(String title,int money, Map<BILLS,Integer> map) {

        for (Map.Entry<BILLS,Integer> entry: map.entrySet()){
            if (entry.getValue() != 0){
                int value = entry.getValue();
                System.out.println(entry.getKey() + ": " + "count " + value);
            }
        }
        System.out.println(title + " " + map);
    }
}
