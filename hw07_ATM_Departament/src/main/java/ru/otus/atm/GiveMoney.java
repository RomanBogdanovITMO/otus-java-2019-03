package ru.otus.atm;

import java.util.Map;
// выдавать запрошенную сумму (полиморфизм)
public class GiveMoney implements Operation {
    @Override
    public void action(String title, int money, Map<BILLS,Integer> map) {
        int count = 0;
        boolean flag = false;
        for (Map.Entry<BILLS,Integer> entry: map.entrySet()){
            if (entry.getKey().getValue() == money){
                count++;
                System.out.println(title + " " + entry.getKey());
                int countMapBancknot = entry.getValue() - count;
                entry.setValue(countMapBancknot);
                flag = true;
            }
        }
        if (flag == false){
            System.out.println("ATM is empty");
        }
        System.out.println("ATM " + map);
    }
}
