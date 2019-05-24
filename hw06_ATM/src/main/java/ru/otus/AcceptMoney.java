package ru.otus;

import java.util.Map;

public class AcceptMoney implements Operation {
    @Override
    public void action(String title, int money,Map<Integer,Integer> map) {
        int count = 0;
        for (Map.Entry<Integer,Integer> entry: map.entrySet()){
            if (entry.getKey()== money){
                count++;
                entry.setValue(count);
            }
        }

        System.out.println(title + " " + map);
    }
}
