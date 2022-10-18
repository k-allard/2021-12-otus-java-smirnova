package ru.cashmachine;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class ATM implements ATMInterface {

    private final List<Cassette> cassettes;

    public ATM() {
        CassettesFactory cassettesFactory = new CassettesFactory();
        cassettes = cassettesFactory.createCassettes();
    }

    public int getCashBalance() {
        int balance = 0;
        for (Cassette cassette : cassettes) {
            balance += (cassette.getAmountOfBills() * cassette.getDenominationValue());
        }
        return balance;
    }

    public void depositCash(int amountOfBills, Denomination denomination) {
        for (Cassette cassette : cassettes) {
            if (cassette.getDenominationValue() == denomination.getBillValue()) {
                cassette.addBills(amountOfBills);
                break;
            }
        }
        log.info("{} USD successfully deposited", amountOfBills * denomination.getBillValue());
    }

    public void withdrawCash(int amountToWithdraw) {
        if (amountToWithdraw > getCashBalance()) {
            log.error("{} USD cannot be withdrawn. Not enough cash in the ATM!", amountToWithdraw);
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
        log.debug("All bills in the ATM: " + allBillsInATM);
        return allBillsInATM;
    }

    private ArrayList<Integer> getCombinationOfBills(int targetSum) {
        ArrayList<Integer> resultCombination = new ArrayList<>();
        sum_up_recursive(getAllBillsInATM(), targetSum, new ArrayList<>(), resultCombination);
        if (resultCombination.isEmpty()) {
            log.error("Amount of {} USD is NOT possible to withdraw", targetSum);
        } else {
            log.debug("Amount of {} USD is POSSIBLE to withdraw", targetSum);
            log.debug("Possible combination of bills is: " + resultCombination);
        }
        return resultCombination;
    }

    private void withdrawCashFromRequiredCassettes(ArrayList<Integer> listOfBillsToWithdraw) {
        Map<Integer, Long> billAmountMap = listOfBillsToWithdraw.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (Cassette cassette : cassettes) {
            if (billAmountMap.containsKey(cassette.getDenominationValue())) {
                cassette.removeBills(billAmountMap.get(cassette.getDenominationValue()));
            }
        }
    }

    private boolean sum_up_recursive(
            ArrayList<Integer> numbers, int target, ArrayList<Integer> partial, ArrayList<Integer> result
    ) {
        int current_sum = 0;
        for (int x : partial) {
            current_sum += x;
        }
        if (current_sum == target) {
            result.addAll(partial);
            return false;
        }
        if (current_sum >= target) {
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
