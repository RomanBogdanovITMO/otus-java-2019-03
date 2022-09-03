package ru.otus.atm;

import java.util.Map;

//полиморфизм
public interface Operation {

    void action(String title, int money, Map<BILLS, Integer> map);
}
