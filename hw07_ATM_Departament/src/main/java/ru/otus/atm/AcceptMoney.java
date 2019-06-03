package ru.otus.atm;

import java.util.Map;
// принимать банкноты разных номиналов (полиморфизм)
public class AcceptMoney implements Operation {
    @Override
    public void action(String title, int money, Map<BILLS, Integer> map) {
        int countMoneyInIssue = 0;
        int countMoneyBefore = 0;
        int countMoneyAfter = 0;
        for (Map.Entry<BILLS, Integer> entry : map.entrySet()){
            countMoneyBefore += entry.getValue();
        }
        for (Map.Entry<BILLS, Integer> entry : map.entrySet()) {
            if (entry.getKey().getValue() == money) {
                countMoneyInIssue++;
                entry.setValue(countMoneyInIssue);
            }
            countMoneyAfter += entry.getValue();
        }
        if (countMoneyBefore == countMoneyAfter){
            System.out.println("May be you have not correct BILLS: " + money);
        }


        System.out.println(title + " " + map);
    }
}
