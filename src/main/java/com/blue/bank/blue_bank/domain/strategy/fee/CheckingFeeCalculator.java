package com.blue.bank.blue_bank.domain.strategy.fee;
import com.blue.bank.blue_bank.domain.model.account.AccountType;
import java.math.BigDecimal;
public class CheckingFeeCalculator implements FeeCalculator {
    @Override
    public boolean supports(AccountType accountType) {
        return accountType == AccountType.CHECKING;
    }
    @Override
    public BigDecimal calculate(BigDecimal amount) {
        return amount.multiply(new BigDecimal("0.015"));
    }
}