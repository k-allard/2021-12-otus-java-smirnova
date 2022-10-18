package ru.cashmachine;

public enum Denomination {
    ONE_DOLLAR(1),
    TWO_DOLLARS(2),
    FIVE_DOLLARS(5),
    TEN_DOLLARS(10),
    TWENTY_DOLLARS(20),
    FIFTY_DOLLARS(50),
    HUNDRED_DOLLARS(100);

    private final int billValue;

    Denomination(final int value) {
        billValue = value;
    }

    public int getBillValue() { return billValue; }
}
