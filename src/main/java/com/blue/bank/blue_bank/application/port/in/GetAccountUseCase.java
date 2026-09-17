package com.blue.bank.blue_bank.application.port.in;
import com.blue.bank.blue_bank.domain.model.account.Account;
public interface GetAccountUseCase {
    Account findById(Long id);
}