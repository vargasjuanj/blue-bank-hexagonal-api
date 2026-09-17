package com.blue.bank.blue_bank.domain.strategy.fee;
import com.blue.bank.blue_bank.domain.model.account.AccountType;
import java.math.BigDecimal;
public class SavingsFeeCalculator implements FeeCalculator {
    @Override
    public boolean supports(AccountType accountType) {
        return accountType == AccountType.SAVINGS;
    }
    @Override
    public BigDecimal calculate(BigDecimal amount) {
        return amount.multiply(new BigDecimal("0.01"));
    }
}