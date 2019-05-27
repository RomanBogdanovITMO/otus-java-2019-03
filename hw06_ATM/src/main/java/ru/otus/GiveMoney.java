package ru.otus;

import java.util.Map;

public class GiveMoney implements Operation {
    @Override
    public void action(String title, int money, Map<Integer,Integer> map) {
        int count = 0;
        for (Map.Entry<Integer,Integer> entry: map.entrySet()){
            if (entry.getKey()== money){
                count++;
                System.out.println(title + " " + entry.getKey());
                int countMapBancknot = entry.getValue() - count;
                entry.setValue(countMapBancknot);

            }
        }
        System.out.println("ATM " + map);
    }
}
