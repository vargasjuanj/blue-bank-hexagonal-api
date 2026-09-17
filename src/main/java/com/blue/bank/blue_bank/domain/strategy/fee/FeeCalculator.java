package com.blue.bank.blue_bank.domain.strategy.fee;
import com.blue.bank.blue_bank.domain.model.account.AccountType;
import java.math.BigDecimal;
public interface FeeCalculator {
    boolean supports(AccountType accountType);
    BigDecimal calculate(BigDecimal amount);
}