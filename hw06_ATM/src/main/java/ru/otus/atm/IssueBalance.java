package ru.otus.atm;

import java.util.Map;

public class IssueBalance implements Operation {
    @Override
    public void action(String title,String money, Map<ATM.Valuta,Integer> map) {

        for (Map.Entry<ATM.Valuta,Integer> entry: map.entrySet()){
            if (entry.getValue() != 0){
                int value = entry.getValue();
                System.out.println(entry.getKey() + ": " + "count " + value);
            }
          //  System.out.println(issue);
        }
        System.out.println(title + " " + map);
    }
}
