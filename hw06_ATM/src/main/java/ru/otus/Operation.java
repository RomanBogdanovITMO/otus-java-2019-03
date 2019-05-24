package ru.otus;

import java.util.Map;

public interface Operation {
    void action(String title, int money, Map<Integer,Integer> map);
}
