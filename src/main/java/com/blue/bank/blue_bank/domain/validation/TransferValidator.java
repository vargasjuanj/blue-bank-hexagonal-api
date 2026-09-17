package com.blue.bank.blue_bank.domain.validation;
import com.blue.bank.blue_bank.domain.model.transaction.TransferContext;
public interface TransferValidator {
    void validate(TransferContext ctx);
}