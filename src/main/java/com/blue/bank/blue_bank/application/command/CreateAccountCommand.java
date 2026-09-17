package com.blue.bank.blue_bank.application.command;
import com.blue.bank.blue_bank.domain.model.account.AccountType;
import lombok.Builder;
import java.math.BigDecimal;
@Builder
public record CreateAccountCommand(
        String accountNumber,
        String ownerName,
        String email,
        AccountType type,
        BigDecimal balance
) {
}