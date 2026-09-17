package com.blue.bank.blue_bank.application.port.out;
import com.blue.bank.blue_bank.domain.model.shared.FraudCheckResult;
import java.math.BigDecimal;
public interface FraudCheckPort {
    FraudCheckResult check(Long accountId, BigDecimal amount);
}