package ru.otus.memento;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.atm.ATM;

@Data
@AllArgsConstructor
public class ATMMemento {

    private final ATM atm;

    public ATM getSavedState() {
        return atm;
    }
}
