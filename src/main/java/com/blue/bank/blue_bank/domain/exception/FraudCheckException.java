package com.blue.bank.blue_bank.domain.exception;
public class FraudCheckException extends RuntimeException {
    public FraudCheckException(String reason) {
        super(reason);
    }
}