package com.blue.bank.blue_bank.domain.model.transaction;
import com.blue.bank.blue_bank.domain.model.account.Account;
import java.math.BigDecimal;
public record TransferContext(
        Account from,
        Account to,
        BigDecimal amount
) {
}