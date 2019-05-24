package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class ATM {

    public   Map<Integer,Integer> cell;

    public ATM(){
        cell = new HashMap<>();
        cell.put(100,0);
        cell.put(200,0);
        cell.put(300,0);
    }

    public void start(String tittle, int countMoney, Operation operation){

        operation.action(tittle, countMoney,cell);
    }
}
