package ru.cashmachine;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.depositCash(2, Denomination.FIVE_DOLLARS);
        atm.depositCash(5, Denomination.TEN_DOLLARS);
        atm.depositCash(2, Denomination.TWENTY_DOLLARS);
        atm.depositCash(1, Denomination.HUNDRED_DOLLARS);

        System.out.println(">>>      Balance is " + atm.getCashBalance() + " USD      <<<");

        System.out.println("------------------------------------");
        atm.withdrawCash(206);
        System.out.println("------------------------------------");
        atm.withdrawCash(61);
        System.out.println("------------------------------------");
        atm.withdrawCash(145);
        System.out.println("------------------------------------");

        System.out.println(">>>      Balance is " + atm.getCashBalance() + " USD      <<<");

        System.out.println("------------------------------------");
        atm.withdrawCash(54);
        System.out.println("------------------------------------");
        atm.withdrawCash(25);
        System.out.println("------------------------------------");

        System.out.println(">>>      Balance is " + atm.getCashBalance() + " USD      <<<");

    }
}
