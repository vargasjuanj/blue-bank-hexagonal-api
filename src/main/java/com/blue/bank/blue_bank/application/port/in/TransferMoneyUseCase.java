package com.blue.bank.blue_bank.application.port.in;
import com.blue.bank.blue_bank.application.command.TransferMoneyCommand;
import com.blue.bank.blue_bank.domain.model.transaction.Transaction;
public interface TransferMoneyUseCase {
    Transaction transfer(TransferMoneyCommand command);
}