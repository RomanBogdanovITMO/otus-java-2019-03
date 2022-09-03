package ru.otus.memento;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.atm.ATMNew;

@Data
@AllArgsConstructor
public class ATMMemento {

    private final ATMNew atm;

    //сохраняем состояние атм (мементо)
    public ATMNew getSavedState() {
        return atm;
    }
}
