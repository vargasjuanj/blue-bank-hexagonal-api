package com.blue.bank.blue_bank.domain.strategy.fee;
import com.blue.bank.blue_bank.domain.model.account.AccountType;
import java.math.BigDecimal;
public class PremiumFeeCalculator implements FeeCalculator{
    @Override
    public boolean supports(AccountType accountType) {
        return accountType == AccountType.PREMIUM;
    }
    @Override
    public BigDecimal calculate(BigDecimal amount) {
        return BigDecimal.ZERO;
    }
}