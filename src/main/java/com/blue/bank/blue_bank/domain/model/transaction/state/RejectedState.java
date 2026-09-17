package com.blue.bank.blue_bank.domain.model.transaction.state;
import com.blue.bank.blue_bank.domain.model.transaction.TransactionStatus;
public record RejectedState() implements TransactionState {
    @Override
    public TransactionStatus status() {
        return TransactionStatus.REJECTED;
    }
}