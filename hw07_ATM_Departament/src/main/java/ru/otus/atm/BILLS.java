package ru.otus.atm;


public enum BILLS {
    BILLS_ONE_HUNDRED(100),
    BILLS_TWO_HUNDRED(200),
    BILLS_THREE_HUNDRED(300);
    private int value;

    BILLS(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
