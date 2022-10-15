package ru.cashmachine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        log.info("{} bills of {} USD each successfully withdrawn", amount, denomination.getBillValue());
    }
}
