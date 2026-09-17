package com.blue.bank.blue_bank.domain.exception;
public class AccountNotFoundExcepcion extends RuntimeException {
    public AccountNotFoundExcepcion(Long id) {
        super("No se encontró la cuenta con id " + id);
    }
}