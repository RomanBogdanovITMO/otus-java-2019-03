package ru.otus.atm;

public enum BILLS {
    BILLS_STO(100),
    BILLS_DVESTI(200),
    BILLS_PIYTSOT(300);
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
