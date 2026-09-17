package com.blue.bank.blue_bank.domain.event;
public record AccountClosedEvent(
        Long accountId,
        String accountNumber
) {
}