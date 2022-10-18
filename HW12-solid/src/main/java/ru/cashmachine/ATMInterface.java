package ru.cashmachine;

public interface ATMInterface {

    int getCashBalance();

    void depositCash(int amountOfBills, Denomination denomination);

    void withdrawCash(int amountToWithdraw);
}
