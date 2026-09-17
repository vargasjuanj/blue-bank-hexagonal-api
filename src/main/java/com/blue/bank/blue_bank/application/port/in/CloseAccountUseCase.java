package com.blue.bank.blue_bank.application.port.in;
import com.blue.bank.blue_bank.application.command.CloseAccountCommand;
import com.blue.bank.blue_bank.domain.model.account.Account;
public interface CloseAccountUseCase {
    Account close(CloseAccountCommand command);
}