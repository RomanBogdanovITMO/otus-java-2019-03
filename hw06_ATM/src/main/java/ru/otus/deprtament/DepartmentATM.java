package ru.otus.deprtament;

import ru.otus.atm.ATM;
import ru.otus.visitor.BalanceVisitor;
import ru.otus.visitor.RestoreInitialATMState;


import java.util.ArrayList;
import java.util.List;

public class DepartmentATM {
   private final List<ATM> atms = new ArrayList<>();

   public void addAtm(final ATM atm){
       atms.add(atm);
   }
   public void getBalance(){
       final BalanceVisitor balanceVisitor = new BalanceVisitor();
       atms.forEach(atm -> atm.accept(balanceVisitor));
   }

    public void restoreAllATM(){
        final RestoreInitialATMState restoreInitialATMState = new RestoreInitialATMState();
        atms.forEach(a-> a.accept(restoreInitialATMState));
    }
}
