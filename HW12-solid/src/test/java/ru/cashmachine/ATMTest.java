package ru.cashmachine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ATMTest {

    private final ATM atm = new ATM();

    @BeforeEach
    void depositCashToATM() {
        atm.depositCash(1, Denomination.HUNDRED_DOLLARS);
        atm.depositCash(5, Denomination.TWENTY_DOLLARS);
        atm.depositCash(1, Denomination.FIFTY_DOLLARS);
        atm.depositCash(4, Denomination.ONE_DOLLAR);
    }

    @Test
    @DisplayName("Проверка начального баланса")
    void getCashBalance() {
        assertThat(atm.getCashBalance()).isEqualTo(254);
    }

    @Test
    @DisplayName("Пополнение")
    void depositCash() {
        atm.depositCash(2, Denomination.TEN_DOLLARS);
        assertThat(atm.getCashBalance()).isEqualTo(274);
    }


    @Test
    @DisplayName("Успешный кейс снятия денег")
    void withdrawCashSuccess() {
        atm.withdrawCash(200);
        assertThat(atm.getCashBalance()).isEqualTo(54);
    }

    @Test
    @DisplayName("Неуспешный кейс снятия денег - баланс банкомата ниже")
    void withdrawCashFail() {
        atm.withdrawCash(255);
        assertThat(atm.getCashBalance()).isEqualTo(254);
    }

    @Test
    @DisplayName("Неуспешный кейс снятия денег - нет нужных купюр")
    void withdrawCashFail2() {
        atm.withdrawCash(245);
        assertThat(atm.getCashBalance()).isEqualTo(254);
    }
}
