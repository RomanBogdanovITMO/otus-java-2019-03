package ru.otus;

import java.util.Map;

public class IssueBalance implements operation {
    @Override
    public void action(String title,int money, Map<Integer,Integer> map) {
        int issue = 0;
        for (Map.Entry<Integer,Integer> entry: map.entrySet()){
            if (entry.getValue() != 0){
                int a = entry.getValue();
                int b = a * entry.getKey();
                issue += b;
               // System.out.println("first" + issue);
            }
          //  System.out.println(issue);
        }
        System.out.println("result " + issue);
    }
}
