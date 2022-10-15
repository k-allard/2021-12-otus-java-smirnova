package ru.cashmachine;

public class Cassette {
    private final Denomination denomination;

    private long amountOfBills;

    Cassette(Denomination denomination) {
        this.denomination = denomination;
        amountOfBills = 0;
    }

    int getDenominationValue() {
        return denomination.getBillValue();
    }

    long getAmountOfBills() {
        return amountOfBills;
    }

    void addBills(long amount) {
        amountOfBills += amount;
    }

    void removeBills(long amount) {
        amountOfBills -= amount;
        System.out.printf("\"%d\" bills of \"%d\" USD each successfully withdrawn.%n", amount, denomination.getBillValue());
    }
}
