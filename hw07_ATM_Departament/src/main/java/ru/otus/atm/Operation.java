package ru.otus.atm;
import java.util.Map;
//полиморфизм
public interface Operation {
    void action(String title, String money, Map<Bills, Integer> map);
}
