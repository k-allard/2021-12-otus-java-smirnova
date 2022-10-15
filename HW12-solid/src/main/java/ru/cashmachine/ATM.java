package ru.cashmachine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ATM {

    private final LinkedList<Cassette> cassettes;

    ATM() {
        CassettesFactory cassettesFactory = new CassettesFactory();
        cassettes = cassettesFactory.createCassettes();
    }

    int getCashBalance() {
        int balance = 0;
        for (Cassette cassette : cassettes) {
            balance += (cassette.getAmountOfBills() * cassette.getDenominationValue());
        }
        return balance;
    }

    void depositCash(int amountOfBills, Denomination denomination) {
        for (Cassette cassette : cassettes) {
            if (cassette.getDenominationValue() == denomination.getBillValue()) {
                cassette.addBills(amountOfBills);
                break;
            }
        }
        System.out.printf("%d USD successfully deposited.%n", amountOfBills * denomination.getBillValue());
    }

    void withdrawCash(int amountToWithdraw) {
        if (amountToWithdraw > getCashBalance()) {
            System.out.printf("%d USD cannot be withdrawn. Not enough cash in the ATM!%n", amountToWithdraw);
            return;
        }

        ArrayList<Integer> combinationOfBills = getCombinationOfBills(amountToWithdraw);
        if (combinationOfBills.isEmpty()) {
            return;
        }
        withdrawCashFromRequiredCassettes(combinationOfBills);
    }

    private ArrayList<Integer> getAllBillsInATM() {
        ArrayList<Integer> allBillsInATM = new ArrayList<>();
        for (Cassette cassette : cassettes) {
            for (int i = 0; i < cassette.getAmountOfBills(); i++) {
                allBillsInATM.add(cassette.getDenominationValue());
            }
        }
        System.out.println("All bills in the ATM: " + allBillsInATM);
        return allBillsInATM;
    }

    private ArrayList<Integer> getCombinationOfBills(int targetSum) {
        ArrayList<Integer> resultCombination = new ArrayList<>();
        sum_up_recursive(getAllBillsInATM(), targetSum, new ArrayList<>(), resultCombination);
        if (resultCombination.isEmpty()) {
            System.out.printf("Amount of \"%d\" USD is NOT possible to withdraw.%n", targetSum);
        } else {
            System.out.printf("Amount of \"%d\" USD is POSSIBLE to withdraw.%n", targetSum);
            System.out.println("First found possible combination is: " + resultCombination);
        }
        return resultCombination;
    }

    private void withdrawCashFromRequiredCassettes(ArrayList<Integer> listOfBillsToWithdraw) {
        Map<Integer, Long> billAmountMap = listOfBillsToWithdraw.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("How many bills of each denomination need to be withdrawn: " + billAmountMap);

        for (Cassette cassette : cassettes) {
            if (billAmountMap.containsKey(cassette.getDenominationValue())) {
                cassette.removeBills(billAmountMap.get(cassette.getDenominationValue()));
            }
        }

        System.out.println("Cash successfully withdrawn from the ATM!");
    }

    private boolean sum_up_recursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial, ArrayList<Integer> result) {
        int s = 0;
        for (int x : partial) {
            s += x;
        }
        if (s == target) {
            result.addAll(partial);
            return false;
        }
        if (s >= target) {
            return true;
        }
        for (int i = 0; i < numbers.size(); i++) {
            ArrayList<Integer> remaining = new ArrayList<>();
            int n = numbers.get(i);
            for (int j = i + 1; j < numbers.size(); j++) {
                remaining.add(numbers.get(j));
            }
            ArrayList<Integer> partial_rec = new ArrayList<>(partial);
            partial_rec.add(n);
            if (!sum_up_recursive(remaining, target, partial_rec, result)) {
                return false;
            }
        }
        return true;
    }
}
