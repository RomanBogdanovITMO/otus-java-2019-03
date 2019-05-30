package ru.otus.atm;

import java.util.Map;
// принимать банкноты разных номиналов (полиморфизм)
public class AcceptMoney implements Operation {
    @Override
    public void action(String title, String money, Map<Bills, Integer> map) {
        int count = 0;
        for (Map.Entry<Bills, Integer> entry : map.entrySet()) {
            if (entry.getKey().toString().equals(money)) {
                count++;
                entry.setValue(count);
            }
        }

        System.out.println(title + " " + map);
    }
}
