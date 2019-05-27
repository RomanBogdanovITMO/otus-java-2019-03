package ru.otus.atm;
import java.util.Map;

public interface Operation {
    void action(String title, String money, Map<ATM.Valuta, Integer> map);
}
