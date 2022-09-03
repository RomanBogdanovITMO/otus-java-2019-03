package ru.otus.atm;
import java.util.Map;

public interface Operation {
    void action(final String title, final String money, final Map<ATM.Valuta,Integer> map);
}
